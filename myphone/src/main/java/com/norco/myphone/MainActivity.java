package com.norco.myphone;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import java.util.List;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
    static final String TAG = "MyPhone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSystemPhoneMessage();
    }

    private void getSystemPhoneMessage(){

        TelephonyManager telephonyManager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //手机串号:GSM手机的 IMEI 和 CDMA手机的 MEID.
        String deviceID =telephonyManager.getDeviceId();
        //手机号(有些手机号无法获取，是因为运营商在SIM中没有写入手机号)
        String tel =telephonyManager.getLine1Number();
        //获取手机SIM卡的序列号
        String imei =telephonyManager.getSimSerialNumber();
        //获取客户id，在gsm中是imsi号
        String imsi=telephonyManager.getSubscriberId();
        //电话方位
        CellLocation str =telephonyManager.getCellLocation();
        //运营商名称,注意：仅当用户已在网络注册时有效,在CDMA网络中结果也许不可靠
        String networkoperatorName = telephonyManager.getNetworkOperatorName();
        //取得和语音邮件相关的标签，即为识别符
        String voiceMail =telephonyManager.getVoiceMailAlphaTag();
        //获取语音邮件号码：
        String voiceMailNumber =telephonyManager.getVoiceMailNumber();
        //获取ISO国家码，相当于提供SIM卡的国家码。
        String simCountryIso =telephonyManager.getSimCountryIso();

        /**
         * 电话状态：
         * 1.tm.CALL_STATE_IDLE=0         无活动
         *2.tm.CALL_STATE_RINGING=1  响铃
         *3.tm.CALL_STATE_OFFHOOK=2  摘机
         */
        int callState =telephonyManager.getCallState();

        /**
         * 设备的软件版本号：
         * 例如：the IMEI/SV(softwareversion) for GSM phones.
         * Return null if the software versionis not available.
         */
        String devicesoftware =telephonyManager.getDeviceSoftwareVersion();

        /**
         * 获取ISO标准的国家码，即国际长途区号。
         * 注意：仅当用户已在网络注册后有效。
         *      在CDMA网络中结果也许不可靠。
         */
        String networkCountry =telephonyManager.getNetworkCountryIso();

        /**
         * MCC+MNC(mobile country code +mobile network code)
         * 注意：仅当用户已在网络注册时有效。
         *    在CDMA网络中结果也许不可靠。
         */
        String networkoperator = telephonyManager.getNetworkOperator();

        /**
         * 当前使用的网络类型：
         * 例如：NETWORK_TYPE_UNKNOWN  网络类型未知  0
         NETWORK_TYPE_GPRS    GPRS网络  1
         NETWORK_TYPE_EDGE    EDGE网络  2
         NETWORK_TYPE_UMTS    UMTS网络  3
         NETWORK_TYPE_HSDPA    HSDPA网络  8
         NETWORK_TYPE_HSUPA    HSUPA网络  9
         NETWORK_TYPE_HSPA    HSPA网络  10
         NETWORK_TYPE_CDMA    CDMA网络,IS95A 或 IS95B.  4
         NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5
         NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6
         NETWORK_TYPE_1xRTT   1xRTT网络  7
         */
        int netWorkType =telephonyManager.getNetworkType();
        Log.e(TAG, "netWorkType  " + netWorkType);
        /**
         * 手机类型：
         * 例如：PHONE_TYPE_NONE  无信号
         PHONE_TYPE_GSM   GSM信号
         PHONE_TYPE_CDMA  CDMA信号
         */
        int phoneType = telephonyManager.getPhoneType();
        Log.e(TAG, "phoneType  " + phoneType);
        /**
         * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
         * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
         */
        String simOperator =telephonyManager.getSimOperator();

        /**
         * 服务商名称：
         * 例如：中国移动、联通
         * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
         */
        String simOperatorName =telephonyManager.getSimOperatorName();
        Log.e(TAG, "simOperatorName  " + simOperatorName);
        /**
         * SIM的状态信息：
         * SIM_STATE_UNKNOWN         未知状态 0
         SIM_STATE_ABSENT          没插卡 1
         SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2
         SIM_STATE_PUK_REQUIRED    锁定状态，需要用户的PUK码解锁 3
         SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4
         SIM_STATE_READY           就绪状态 5
         */
        int simStat =telephonyManager.getSimState();
        Log.e(TAG, "simStat" + simStat);
        /**
         * ICC卡是否存在
         */
        boolean bl=telephonyManager.hasIccCard();
        /**
         * 是否漫游:
         * (在GSM用途下)
         */
        boolean blean =telephonyManager.isNetworkRoaming();
        /**
         * 附近的电话的信息:
         * 类型：List<NeighboringCellInfo>
         * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
         */
        List<NeighboringCellInfo> list =telephonyManager.getNeighboringCellInfo();//List<NeighboringCellInfo>
        /**
         * 获取数据连接状态
         */
        int dataActivty =telephonyManager.getDataActivity();
    }
}/*
    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
*//**
 * 返回电话状态
 *
 * CALL_STATE_IDLE 无任何状态时
 * CALL_STATE_OFFHOOK 接起电话时
 * CALL_STATE_RINGING 电话进来时
 *//*
tm.getCallState();
        //返回当前移动终端的位置
        CellLocation location=tm.getCellLocation();
        //请求位置更新，如果更新将产生广播，接收对象为注册LISTEN_CELL_LOCATION的对象，需要的permission名称为ACCESS_COARSE_LOCATION。
        location.requestLocationUpdate();
*
         * 获取数据活动状态
         *
         * DATA_ACTIVITY_IN 数据连接状态：活动，正在接受数据
         * DATA_ACTIVITY_OUT 数据连接状态：活动，正在发送数据
         * DATA_ACTIVITY_INOUT 数据连接状态：活动，正在接受和发送数据
         * DATA_ACTIVITY_NONE 数据连接状态：活动，但无数据发送和接受


        tm.getDataActivity();
*
         * 获取数据连接状态
         *
         * DATA_CONNECTED 数据连接状态：已连接
         * DATA_CONNECTING 数据连接状态：正在连接
         * DATA_DISCONNECTED 数据连接状态：断开
         * DATA_SUSPENDED 数据连接状态：暂停


        tm.getDataState();
*
         * 返回当前移动终端的唯一标识
         *
         * 如果是GSM网络，返回IMEI；如果是CDMA网络，返回MEID


        tm.getDeviceId();
        //返回移动终端的软件版本，例如：GSM手机的IMEI/SV码。
        tm.getDeviceSoftwareVersion();
        //返回手机号码，对于GSM网络来说即MSISDN
        tm.getLine1Number();
        //返回当前移动终端附近移动终端的信息
        List<NeighboringCellInfo> infos=tm.getNeighboringCellInfo();
        for(NeighboringCellInfo info:infos){
        //获取邻居小区号
        int cid=info.getCid();
        //获取邻居小区LAC，LAC: 位置区域码。为了确定移动台的位置，每个GSM/PLMN的覆盖区都被划分成许多位置区，LAC则用于标识不同的位置区。
        info.getLac();
        info.getNetworkType();
        info.getPsc();
        //获取邻居小区信号强度
        info.getRssi();
        }
        //返回ISO标准的国家码，即国际长途区号
        tm.getNetworkCountryIso();
        //返回MCC+MNC代码 (SIM卡运营商国家代码和运营商网络代码)(IMSI)
        tm.getNetworkOperator();
        //返回移动网络运营商的名字(SPN)
        tm.getNetworkOperatorName();
*
         * 获取网络类型
         *
         * NETWORK_TYPE_CDMA 网络类型为CDMA
         * NETWORK_TYPE_EDGE 网络类型为EDGE
         * NETWORK_TYPE_EVDO_0 网络类型为EVDO0
         * NETWORK_TYPE_EVDO_A 网络类型为EVDOA
         * NETWORK_TYPE_GPRS 网络类型为GPRS
         * NETWORK_TYPE_HSDPA 网络类型为HSDPA
         * NETWORK_TYPE_HSPA 网络类型为HSPA
         * NETWORK_TYPE_HSUPA 网络类型为HSUPA
         * NETWORK_TYPE_UMTS 网络类型为UMTS
         *
         * 在中国，联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA，电信的3G为EVDO


        tm.getNetworkType();
*
         * 返回移动终端的类型
         *
         * PHONE_TYPE_CDMA 手机制式为CDMA，电信
         * PHONE_TYPE_GSM 手机制式为GSM，移动和联通
         * PHONE_TYPE_NONE 手机制式未知


        tm.getPhoneType();
        //返回SIM卡提供商的国家代码
        tm.getSimCountryIso();
        //返回MCC+MNC代码 (SIM卡运营商国家代码和运营商网络代码)(IMSI)
        tm.getSimOperator();
        tm.getSimOperatorName();
        //返回SIM卡的序列号(IMEI)
        tm.getSimSerialNumber();
*
         * 返回移动终端
         *
         * SIM_STATE_ABSENT SIM卡未找到
         * SIM_STATE_NETWORK_LOCKED SIM卡网络被锁定，需要Network PIN解锁
         * SIM_STATE_PIN_REQUIRED SIM卡PIN被锁定，需要User PIN解锁
         * SIM_STATE_PUK_REQUIRED SIM卡PUK被锁定，需要User PUK解锁
         * SIM_STATE_READY SIM卡可用
         * SIM_STATE_UNKNOWN SIM卡未知


        tm.getSimState();
        //返回用户唯一标识，比如GSM网络的IMSI编号
        tm.getSubscriberId();
        //获取语音信箱号码关联的字母标识。
        tm.getVoiceMailAlphaTag();
        //返回语音邮件号码
        tm.getVoiceMailNumber();
        tm.hasIccCard();
        //返回手机是否处于漫游状态
        tm.isNetworkRoaming();
// tm.listen(PhoneStateListener listener, int events) ;

//解释：
//IMSI是国际移动用户识别码的简称(International Mobile Subscriber Identity)
//IMSI共有15位，其结构如下：
//MCC+MNC+MIN
//MCC：Mobile Country Code，移动国家码，共3位，中国为460;
//MNC:Mobile NetworkCode，移动网络码，共2位
//在中国，移动的代码为电00和02，联通的代码为01，电信的代码为03
//合起来就是（也是Android手机中APN配置文件中的代码）：
//中国移动：46000 46002
//中国联通：46001
//中国电信：46003
//举例，一个典型的IMSI号码为460030912121001

//IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
//IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
//其组成为：
//1. 前6位数(TAC)是”型号核准号码”，一般代表机型
//2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
//3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
//4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
*/

/*
国内运营商使用的网络分别是：
        中国联通2G/3G/4G对应的网络是GSM/WCDMA/FDD-LTE。
        中国移动2G/3G/4G对应的网络是GSM/TD-SCDMA/TD-LTE。
        中国电信2G/3G/4G对应的网络是CDMA/CDMA2000/FDD-LTE。
        以下是关于这些网络的简单说明：
        1、CDMA (Code Division Multiple Access) 又称码分多址，
        是在数字技术的分支--扩频通信技术上发展起来的一种崭新而成熟的无线通信技术。
        CDMA技术的原理是基于扩频技术，即将需传送的具有一定信号带宽信息数据，用一个带宽
        远大于信号带宽的高速伪随机码进行调制，使原数据信号的带宽被扩展，再经载波调制并发送出去。
        接收端使用完全相同的伪随机码，与接收的带宽信号作相关处理，把宽带信号换成原信息数据的窄带信号即解扩，以实现信息通信。

        2、GSM是全球移动通信系统(Global System for Mobile communications) 的简称,
        由欧洲电信标准组织ETSI制订的一个数字移动通信标准，。它的空中接口采用时分多址技术。

        3、UMTS是通用移动通信系统的简称(Universal Mobile Telecommunications System)，
        UMTS作为一个完整的3G移动通信技术标准，UMTS并不仅限于定义空中接口。除WCDMA作为首选空中接口技术获得不断完善外，UMTS还相继引入了TD-SCDMA和HSDPA技术。

        4、TD-SCDMA是英文Time Division-Synchronous Code Division Multiple Access（时分同步码分多址）
         的简称，中国提出的第三代移动通信标准(简称3G)，也是ITU批准的三个3G标准中的一个，以我国知识产权为主的、被国际上广泛接受和认可的无线通信国际标准。
        5、LTE（Long Term Evolution，长期演进)是由3GPP（The 3rd Generation Partnership Project，
        第三代合作伙伴计划）组织制定的UMTS（Universal Mobile Telecommunications System，通用移动通信系统）技术标准的长期演进，于2004年12月在3GPP多伦多会议上正式立项并启动。

*/



