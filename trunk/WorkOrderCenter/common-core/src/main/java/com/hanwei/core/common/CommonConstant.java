package com.hanwei.core.common;

/**
 * @author CX
 * @Description: 通用常量
 */
public interface CommonConstant {

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 1;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = 0;

    /**
     * YES
     */
    String CHAR_YES = "Y";

    /**
     * NO
     */
    String CHAR_NO = "N";

    /**
     * 正常状态
     */
    String UNTILS_STATUS_NORMAL = "1";

    /**
     * 临期状态
     */
    String UNTILS_STATUS_ADVENT = "2";

    /**
     * 过期状态
     */
    String UNTILS_STATUS_EXPIRED = "3";

    /**
     * 禁用状态
     */
    String UNTILS_STATUS_DISABLE = "0";

    /**
     * 删除标志
     */
    Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    Integer DEL_FLAG_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 导入
     */
    int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    int OPERATE_TYPE_6 = 6;


    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    Integer SC_OK_200 = 200;
    Integer SC_BAD_REQUEST_400 = 400;
    Integer SC_UNAUTHORIZED_401 = 401;
    Integer SC_FORBIDDEN_403 = 403;
    Integer SC_NOT_FOUND_404 = 404;
    /**
     * 权限认证失败
     */
    Integer SC_NO_AUTHZ_510 = 510;

    /**
     * 优先级（L低，M中，H高）
     */
    String PRIORITY_L = "L";
    String PRIORITY_M = "M";
    String PRIORITY_H = "H";

    /**===============================================================================================*/
    String X_ACCESS_TOKEN = "X-Access-Token";
    String X_SIGN = "X-Sign";
    String X_TIMESTAMP = "X-TIMESTAMP";
    /**
     * 租户 请求头
     */
    String TENANT_ID = "tenant-id";
    /**
     * ===============================================================================================
     */

    String TOKEN_IS_INVALID_MSG = "Token失效，请重新登录!";
    String X_FORWARDED_SCHEME = "X-Forwarded-Scheme";

    /**
     * POST请求
     */
    String HTTP_POST = "POST";

    /**
     * PUT请求
     */
    String HTTP_PUT = "PUT";

    /**
     * PATCH请求
     */
    String HTTP_PATCH = "PATCH";

    /**
     * 未知的
     */
    String UNKNOWN = "unknown";

    /**
     * 字符串http
     */
    String STR_HTTP = "http";

    /**
     * String 类型的空值
     */
    String STRING_NULL = "null";

    /**
     * 前端vue3版本Header参数名
     */
    String VERSION = "X-Version";

    /**
     * http:// http协议
     */
    String HTTP_PROTOCOL = "http://";

    /**
     * https:// https协议
     */
    String HTTPS_PROTOCOL = "https://";

    /**字典翻译文本后缀*/
    String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    String UPLOAD_TYPE_LOCAL = "local";
    String UPLOAD_TYPE_MINIO = "minio";
    String UPLOAD_TYPE_OSS = "alioss";

    /**
     * gateway通过header传递根路径 basePath
     */
    String X_GATEWAY_BASE_PATH = "X_GATEWAY_BASE_PATH";

    /**
     * 微服务读取配置文件属性 服务地址
     */
    String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";


}
