package com.miles.testexport.com.miles.demo;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 2018/8/28 14:33
 */

public class WordCounter {

        //将一行按空格什么的分开，此方法只适用于英文
        //中文的话需要分词
        String[] wordsIn(String line) {
            return line.trim().split("(\\s|\\p{Punct})+");
        }

        //在一个文档中搜索一个单词，思路较为简单，将每一行按照空格分开（中文需要分词）
        //然后逐个匹配
        Long occurrencesCount(Document document, String searchedWord) {
            long count = 0;
            for (String line : document.getLines()) {
                for (String word : wordsIn(line)) {
                    if (searchedWord.equals(word)) {
                        count = count + 1;
                    }
                }
            }
            return count;
        }

        //搜索文件Document的任务，RecursiveTask有返回值，尖括号<Long>表示返回值的类型为Long
        class DocumentSearchTask extends RecursiveTask<Long> {
            private final Document document;
            private final String searchedWord;

            DocumentSearchTask(Document document, String searchedWord) {
                super();
                this.document = document;
                this.searchedWord = searchedWord;
            }

            @Override
            protected Long compute() {      //即在一个文档中搜索，并返回值
                return occurrencesCount(document, searchedWord);
            }
        }

        //搜索文件夹的任务
        class FolderSearchTask extends RecursiveTask<Long> {
            private final Folder folder;
            private final String searchedWord;

            FolderSearchTask(Folder folder, String searchedWord) {
                super();
                this.folder = folder;
                this.searchedWord = searchedWord;
            }

            @Override
            protected Long compute() {
                long count = 0L;

                //保存用于创建的子任务数
                List<RecursiveTask<Long>> forks = new LinkedList<>();

                //用搜索文件夹的子任务去搜索文件夹
                for (Folder subFolder : folder.getSubFolders()) {
                    FolderSearchTask task = new FolderSearchTask(subFolder, searchedWord);
                    forks.add(task);
                    task.fork();
                }
                //用搜索文档的子任务去搜索文档
                for (Document document : folder.getDocuments()) {
                    DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);
                    forks.add(task);
                    task.fork();
                }
                for (RecursiveTask<Long> task : forks) {
                    count = count + task.join();
                }
                return count;
            }
        }

        //单线程方式搜素，作为对比
        Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
            long count = 0;
            for (Folder subFolder : folder.getSubFolders()) {
                count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
            }
            for (Document document : folder.getDocuments()) {
                count = count + occurrencesCount(document, searchedWord);
            }
            return count;
        }

        /* ......................................................................................... */

        private final ForkJoinPool forkJoinPool = new ForkJoinPool();

        //多线程搜索
        Long countOccurrencesInParallel(Folder folder, String searchedWord) {
            return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
        }

        /* ......................................................................................... */

        public static void main(String[] args) throws IOException {
            WordCounter wordCounter = new WordCounter();

            String path = "E:\\test";
            String word = "int";

            Folder folder = Folder.fromDirectory(new File(path));

            //重复计算次数
            final int repeatCount = Integer.decode("1");
            long counts;
            long startTime;
            long stopTime;

            long[] singleThreadTimes = new long[repeatCount];
            long[] forkedThreadTimes = new long[repeatCount];

            for (int i = 0; i < repeatCount; i++) {
                startTime = System.currentTimeMillis();
                counts = wordCounter.countOccurrencesOnSingleThread(folder, word);
                stopTime = System.currentTimeMillis();
                singleThreadTimes[i] = (stopTime - startTime);
                System.out.println(counts + " , single thread search took " + singleThreadTimes[i] + "ms");
            }

            counts = 0;
            for (int i = 0; i < repeatCount; i++) {
                startTime = System.currentTimeMillis();
                counts = wordCounter.countOccurrencesInParallel(folder, word);
                stopTime = System.currentTimeMillis();
                forkedThreadTimes[i] = (stopTime - startTime);
                System.out.println(counts + " , fork / join search took " + forkedThreadTimes[i] + "ms");
            }

            System.out.println();
        }

    }
