package com.miles.testexport.com.miles.demo;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
 
//文件夹类，包含文档和一些子文件夹
public class Folder {
 
    private final List<Folder> subFolders;
    private final List<Document> documents;
 
    Folder(List<Folder> subFolders, List<Document> documents) {
        this.subFolders = subFolders;
        this.documents = documents;
    }
 
    List<Folder> getSubFolders() {
        return this.subFolders;
    }
 
    List<Document> getDocuments() {
        return this.documents;
    }
 
    //静态方法，返回一个 文件夹Folder
    static Folder fromDirectory(File dir) throws IOException {
 
        List<Document> documents = new LinkedList<>();
        List<Folder> subFolders = new LinkedList<>();
 
        for (File entry : dir.listFiles()) {   //列出所有的文件
            if (entry.isDirectory()) {         //是一个目录，产生一个递归调用
                subFolders.add(Folder.fromDirectory(entry));
            } else {
                documents.add(Document.fromFile(entry));
            }
        }
        return new Folder(subFolders, documents);
    }
 
}