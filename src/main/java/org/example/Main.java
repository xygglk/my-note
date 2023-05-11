package org.example;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        File inputWord = new File("input.docx"); // 要读取的Word文档
        String outputDir = "output/";
        // 拆分后输出的目录
        try {
            FileInputStream fis = new FileInputStream(inputWord);
            if (inputWord.getName().endsWith(".doc")) {
                // 处理.doc格式的Word文档
                HWPFDocument doc = new HWPFDocument(fis);
                Range range = doc.getRange();
                splitDoc(range, outputDir);
            } else if (inputWord.getName().endsWith(".docx")) {
                // 处理.docx格式的Word文档
                XWPFDocument docx = new XWPFDocument(fis);
                for (XWPFParagraph paragraph : docx.getParagraphs()) {
                    if (paragraph.getStyleID() != null && paragraph.getStyleID().startsWith("Heading2")) {
                        // 以二级标题为分隔符拆分文档
                        String fileName = outputDir + paragraph.getParagraphText().trim() + ".docx";
                        XWPFDocument newDoc = new XWPFDocument();
                        newDoc.createParagraph().createRun().setText(paragraph.getParagraphText());
                        for (XWPFParagraph p : docx.getParagraphs()) {
                            if (p == paragraph) {
                                continue;
                            }
                            if (p.getStyleID() != null && p.getStyleID().startsWith("Heading2")) {
                                break;
                            }
                            newDoc.createParagraph().createRun().setText(p.getParagraphText());
                        }
                        FileOutputStream fos = new FileOutputStream(fileName);
                        newDoc.write(fos);
                        fos.close();
                    }
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void splitDoc(Range range, String outputDir) throws Exception {
        for (int i = 0; i < range.numParagraphs(); i++) {
            if (range.getParagraph(i).getStyleIndex() == 1) {
                // 找到二级标题
                String title = range.getParagraph(i).text().trim();
                if (title.length() > 0) {
                    // 以二级标题为分隔符拆分文档
                    String fileName = outputDir + title + ".doc";
                    HWPFDocument newDoc = new HWPFDocument();
                    newDoc.getRange().insertAfter(title);
                    for (int j = i + 1; j < range.numParagraphs(); j++) {
                        if (range.getParagraph(j).getStyleIndex() == 1) {
                            // 找到下一个二级标题
                            break;
                        }
                        newDoc.getRange().insertAfter(range.getParagraph(j).text());
                    }
                    FileOutputStream fos = new FileOutputStream(fileName);
                    newDoc.write(fos);
                    fos.close();
                }
            }
        }
    }
}