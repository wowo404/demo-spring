package org.liu.demo.mongodb.constant;

/**
 * @author pandalyhyy@163.com
 * @ClassName: AppConstant
 * @Description: 全局常量类
 * @date 2019-5-15 上午11:41
 */
public class Constant {

    public static final String N_POINTVALUE = "nPointValue_";//监控点最新数据集合

    public static final String HISTORY_VALUE_KEY = "HistoryValue";//监控点历史数据

    //监控点趋势数据集合
    public static final String H_FIVE_MIN = "trend_data_five_min_";//5分钟，按监控点分集合
    public static final String H_FIFTEEN_MIN = "trend_data_fifteen_min_";//15分钟，按监控点分集合
    public static final String H_THIRTY_MIN = "trend_data_thirty_min";//30分钟
    public static final String H_ONE_HOUR = "trend_data_one_hour";//1小时
    public static final String H_TWO_HOUR = "trend_data_two_hour";//2小时
    public static final String H_THREE_HOUR = "trend_data_three_hour";//3小时
    public static final String H_SIX_HOUR = "trend_data_six_hour";//6小时
    public static final String H_TWELVE_HOUR = "trend_data_twelve_hour";//12小时
    public static final String H_ONE_DAY = "trend_data_one_day";//1天
    public static final String H_THREE_DAY = "trend_data_three_day";//3天
    public static final String H_SEVEN_DAY = "trend_data_seven_day";//7天
    public static final String H_FOURTEEN_DAY = "trend_data_fourteen_day";//14天
    public static final String H_ONE_MON = "trend_data_one_mon";//1个月
    public static final String H_ONE_MIN = "trend_data_one_min_";//1分钟
    public static final String H_THIRTY_SECOND = "trend_data_thirty_second_";//30秒

    public static final String POINT_VALUE_PREFIX = "point_value_";//监测点数据集合名前缀

    public static final String FORECAST_TIME_PREFIX = "forecast_value_";//预测值集合前缀

    public static final String CLIENT_PARAMS = "client_params_";

    public static final String MANUFACTURE_CONFIGURE = "manufacture_configure_";//智能制造网关配置缓存key

    public static final String WX_SERVICE_APPID = "wx.app.id"; //微信公众号APP ID
    public static final String WX_SERVICE_APPSECRET = "wx.secret.key"; //微信公众号APP Secret
    public static final String WX_WEB_HOST = "wx.web.host"; //微信网站地址
    public static final String WX_TEMPLATE_ID = "wx.template.id";

    public static final String GATEWAY_TLV_INFO = "gateway_tlv_info_";//设备相关信息，格式为：key+设备序列号
    //0x30单次请求实时时域波形，记录最后一次的请求时长，格式为：key+设备序列号
    public static final String TUNNEL_LAST_TIME_DOMAIN_WAVEFORM_TIME = "tunnel_last_time_domain_waveform_time_";
    //最后一次请求时长在缓存中的保存多久，单位是秒
    public static final int TUNNEL_LAST_TIME_LENGTH_CACHE_EXPIRED = 180;

    public static final String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";

    public static final String BEF_WARNING_HISTORY = "BefWarningHistory";

    public static final String DUR_WARNING_HISTORY = "BurWarningHistory";

    public static final String SHAKE_LOSE_VALUE = "shake_lose_value_";

    public static final String SATISFY_WARNING_EXPRESSION_TIME = "satisfy_warning_expression_time_";//数据满足预警表达式的时间

    public static final String NOT_SATISFY_WARNING_EXPRESSION_TIME = "not_satisfy_warning_expression_time_";//数据不满足预警表达式的时间(即转为正常的时间)

    public static final String MONITOR_DCS_INFO_PREFIX = "BIND_CODE_DCS:";//监测点：dcs

    public static final String MONITOR_GATEWAY_INFO_PREFIX = "BIND_CODE";//监测点：网关-数据通道

    public static final String MONITOR_GATEWAY_DI_INFO_PREFIX = "BIND_CODE_DI:";//监测点：网关-DI通道

    public static final String MONITOR_GATEWAY_DO_INFO_PREFIX = "BIND_CODE_DO:";//监测点：网关-DO通道

    public static final String MONITOR_ON_TIME = "monitor_on_time_";//设备启动时间

    public static final String MONITOR_OFF_TIME = "monitor_off_time_";//设备关机时间

    public static final String LAST_ON_OFF_STATUS = "last_on_of_status_";//上次开关量的状态

    public static final String GATEWAY_UPGRADE_FILE_PATH = "gateway_upgrade_file_path_";//网关软件升级文件的路径

    public static final String WARNING_COUNT = "warning_count_";//重复预警时同意预警的重复次数

    public static final String CONDITION_LIST = "condition_list_";//监控点的特征值是否满足预警下对应的条件

    public static final String NOT_SATISFY_CONDITION_LIST = "not_satisfy_condition_list_";//监控点下所有条件是否都达到正常持续时间

    public static final String FIRST_WARNING_TIME = "first_warning_time_";//第一次预警的时间

    public static final String WARNING_HISTORY_DETAILS = "warning_history_details_";//预警条件详情

    public static final String WARNINGSETTING_CONDITION_LIST = "warningsetting_condition_list_";//每个预警下面关联的所有预警条件

    public static final String WARNINGSETTING_LIST = "warningsetting_list";//所有的预警配置集合

    public static final String MODBUS_MASTER_SEND = "modbus_master_send_";//modbus协议发送的数据记录(目前只存储了功能码的起始地址和读取数量)

    public static final String MODBUS_SEND = "modbus_send_";

    public static final String ALLEYWAY_COUNT = "alleyway_count_";//数据通道的数量
}
