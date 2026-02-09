package com.hanwei.core.codegenerator.util.window;

import com.hanwei.core.codegenerator.util.database.DbReadTableUtil;
import com.hanwei.core.codegenerator.util.generate.impl.CodeGenerateOne;
import com.hanwei.core.codegenerator.util.generate.pojo.TableVo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @version : [v1.0]
 * @description : 代码生成器窗口
 * @createTime : [2024/1/4 14:48]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 14:48]
 * @updateRemark : [说明本次修改内容]
 */
public class CodeWindow extends JFrame {
    private static final long serialVersionUID = -5324160085184088010L;
    private String packageName = "test";
    private String entityName = "TestEntity";
    private String tableName = "t00_company";
    private String description = "功能描述";
    private Integer fieldRowNum = 1;
    private String primaryKeyPolicy = "uuid";
    private String sequenceCode = "";

    private String[] primaryKeyOptions = new String[]{"uuid", "identity", "sequence"};

    // 组件声明
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel formStylePanel;
    private JPanel generateOptionsPanel;

    private JTextField packageField;
    private JTextField entityField;
    private JTextField tableField;
    private JComboBox<String> primaryKeyCombo;
    private JLabel sequenceLabel;
    private JTextField sequenceField;
    private JTextField descriptionField;
    private JTextField fieldRowField;
    private JRadioButton drawerRadio;
    private JRadioButton popupRadio;
    private JCheckBox controlCheckbox;
    private JCheckBox vueCheckbox;
    private JCheckBox serviceCheckbox;
    private JCheckBox mapperCheckbox;
    private JCheckBox daoCheckbox;
    private JCheckBox entityCheckbox;
    private JCheckBox deleteOldFilesCheckbox;
    private JLabel messageLabel;
    private JButton generateButton;
    private JButton exitButton;

    public CodeWindow() {
        initializeComponents();
        setupLayout();
        setTitle("智慧水务事业群代码生成器[单表模型]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 650));
        setResizable(false);
        setLocationRelativeTo(null); // 居中显示
    }

    private void initializeComponents() {
        // 安全地设置系统外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // 如果设置外观失败，继续执行，不影响窗体显示
            System.out.println("警告：无法设置系统外观，使用默认外观");
            e.printStackTrace();
        }

        // 创建主面板
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // 创建输入面板
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("代码生成配置"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 添加组件
        int row = 0;

        // 包名
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3; gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("包名（小写）："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        packageField = new JTextField(packageName, 20);
        inputPanel.add(packageField, gbc);

        // 实体类名
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
        inputPanel.add(new JLabel("实体类名（首字母大写）："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        entityField = new JTextField(entityName, 20);
        inputPanel.add(entityField, gbc);

        // 表名
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
        inputPanel.add(new JLabel("表名："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        tableField = new JTextField(tableName, 20);
        inputPanel.add(tableField, gbc);

        // 主键生成策略
//        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
//        inputPanel.add(new JLabel("主键生成策略："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        primaryKeyCombo = new JComboBox<>(primaryKeyOptions);
        primaryKeyCombo.setSelectedItem(primaryKeyPolicy);
        primaryKeyCombo.setEnabled(false); // 暂时禁用
//        inputPanel.add(primaryKeyCombo, gbc);

        // 序列名称（仅当选择sequence时可见）
//        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
//        sequenceLabel = new JLabel("主键SEQUENCE：(Oracle序列名)");
//        inputPanel.add(sequenceLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        sequenceField = new JTextField(sequenceCode, 20);
//        inputPanel.add(sequenceField, gbc);

        // 功能描述
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
        inputPanel.add(new JLabel("功能描述："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        descriptionField = new JTextField(description, 20);
        inputPanel.add(descriptionField, gbc);

        // 行字段数目
//        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
//        inputPanel.add(new JLabel("行字段数目："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        fieldRowField = new JTextField(fieldRowNum.toString(), 20);
//        inputPanel.add(fieldRowField, gbc);

        // 表单风格选择
        gbc.gridx = 0; gbc.gridy = ++row; gbc.weightx = 0.3;
        inputPanel.add(new JLabel("表单风格："), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        formStylePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        drawerRadio = new JRadioButton("抽屉风格表单", true);
        popupRadio = new JRadioButton("弹窗风格表单");
        ButtonGroup styleGroup = new ButtonGroup();
        styleGroup.add(drawerRadio);
        styleGroup.add(popupRadio);
        formStylePanel.add(drawerRadio);
        formStylePanel.add(popupRadio);
        inputPanel.add(formStylePanel, gbc);

        // 生成选项
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.weightx = 1.0;
        generateOptionsPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        generateOptionsPanel.setBorder(BorderFactory.createTitledBorder("生成选项"));

        controlCheckbox = new JCheckBox("Controller", true);
        vueCheckbox = new JCheckBox("Vue", true);
        serviceCheckbox = new JCheckBox("Service", true);
        mapperCheckbox = new JCheckBox("Mapper.xml", true);
        daoCheckbox = new JCheckBox("Dao", true);
        entityCheckbox = new JCheckBox("Entity", true);

        generateOptionsPanel.add(controlCheckbox);
        generateOptionsPanel.add(vueCheckbox);
        generateOptionsPanel.add(serviceCheckbox);
        generateOptionsPanel.add(mapperCheckbox);
        generateOptionsPanel.add(daoCheckbox);
        generateOptionsPanel.add(entityCheckbox);

        inputPanel.add(generateOptionsPanel, gbc);

        // 删除旧文件选项
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        deleteOldFilesCheckbox = new JCheckBox("是否删除文件夹内旧文件", true);
        inputPanel.add(deleteOldFilesCheckbox, gbc);

        // 提示标签
        gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(messageLabel.getFont().deriveFont(Font.BOLD));
        inputPanel.add(messageLabel, gbc);

        // 按钮面板
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        // 生成代码按钮 - 使用更深的蓝色和白色文字
        generateButton = new JButton("生成代码");
        generateButton.setBackground(new Color(30, 100, 170));  // 更深的蓝色
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setBorderPainted(false);  // 移除边框
        generateButton.setContentAreaFilled(true);  // 确保填充背景
        generateButton.setPreferredSize(new Dimension(100, 35));
        generateButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));  // 设置字体
        generateButton.addActionListener(e -> generateCode());

        // 退出按钮 - 使用更深的红色和白色文字
        exitButton = new JButton("退出");
        exitButton.setBackground(new Color(180, 30, 40));  // 更深的红色
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);  // 移除边框
        exitButton.setContentAreaFilled(true);  // 确保填充背景
        exitButton.setPreferredSize(new Dimension(100, 35));
        exitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));  // 设置字体
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(generateButton);
        buttonPanel.add(exitButton);

    }

    private void setupLayout() {
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }

    private void generateCode() {
        // 验证输入
        if (packageField.getText().trim().isEmpty()) {
            showMessage("包名不能为空！", true);
            return;
        }
        if (entityField.getText().trim().isEmpty()) {
            showMessage("实体类名不能为空！", true);
            return;
        }
        if (descriptionField.getText().trim().isEmpty()) {
            showMessage("描述不能为空！", true);
            return;
        }
        if (tableField.getText().trim().isEmpty()) {
            showMessage("表名不能为空！", true);
            return;
        }

        // 更新实例变量
        this.packageName = packageField.getText().trim();
        this.entityName = entityField.getText().trim();
        this.description = descriptionField.getText().trim();
        this.tableName = tableField.getText().trim();
        this.primaryKeyPolicy = (String) primaryKeyCombo.getSelectedItem();

        // 如果主键策略是sequence，验证序列名
        if ("sequence".equals(this.primaryKeyPolicy)) {
            if (sequenceField.getText().trim().isEmpty()) {
                showMessage("主键生成策略为sequence时，序列号不能为空！", true);
                return;
            }
            this.sequenceCode = sequenceField.getText().trim();
        }

        try {
            // 尝试解析行字段数
            try {
                this.fieldRowNum = Integer.parseInt(fieldRowField.getText().trim());
            } catch (NumberFormatException ex) {
                showMessage("行字段数目必须是数字！", true);
                return;
            }

            // 检查表是否存在
            boolean tableExists = DbReadTableUtil.c(this.tableName);
            if (tableExists) {
                TableVo tableVo = new TableVo();
                tableVo.setTableName(this.tableName);
                tableVo.setPrimaryKeyPolicy(this.primaryKeyPolicy);
                tableVo.setEntityPackage(this.packageName);
                tableVo.setEntityName(this.entityName);
                tableVo.setFieldRowNum(this.fieldRowNum);
                tableVo.setSequenceCode(this.sequenceCode);
                tableVo.setFtlDescription(this.description);

                if (deleteOldFilesCheckbox.isSelected()) {
                    String path = com.hanwei.core.codegenerator.util.a.a.f;
                    // 生成前先删除文件夹里所有已经生成过的文件
                    org.apache.commons.io.FileUtils.cleanDirectory(new java.io.File(path));
                }

                new CodeGenerateOne(tableVo).generateCodeFile(null);
                showMessage("成功生成增删改查->功能：" + this.description, false);
            } else {
                showMessage("表[" + this.tableName + "] 在数据库中，不存在", true);
                System.err.println(" ERROR ：   表 [ " + this.tableName + " ] 在数据库中，不存在 ！请确认数据源配置是否配置正确、表名是否填写正确~ ");
            }
        } catch (Exception ex) {
            showMessage(ex.getMessage(), true);
        }
    }

    private void showMessage(String message, boolean isError) {
        messageLabel.setText(message);
        messageLabel.setForeground(isError ? Color.RED : new Color(0, 128, 0));
    }

    public static void main(String[] args) {
        // 使用 SwingUtilities.invokeLater 确保在 EDT 中运行
        SwingUtilities.invokeLater(() -> {
            try {
                // 尝试设置系统外观，但如果失败则继续运行
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("警告：无法设置系统外观，使用默认外观");
                e.printStackTrace();
            }

            CodeWindow window = new CodeWindow();
            window.setVisible(true); // 确保窗体显示
        });
    }
}
