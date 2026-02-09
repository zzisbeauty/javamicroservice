package com.hanwei.core.codegenerator;

import com.hanwei.core.codegenerator.util.window.CodeWindow;

import javax.swing.*;

/**
 * @Description: : [代码生成器入口]
 */
public class CodeGeneratorApplication {
    public static void main(String[] args) {
        System.out.println("启动代码生成器...");
        SwingUtilities.invokeLater(() -> {
            System.out.println("进入事件调度线程...");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("外观设置失败，使用默认值");
            }

            CodeWindow window = new CodeWindow();
            window.setVisible(true);
        });
    }

}
