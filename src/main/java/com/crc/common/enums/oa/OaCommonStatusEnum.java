package com.crc.common.enums.oa;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PurchaseStatusEnum
 * @Description:
 * @Author: xiaoming
 * @Date: 2021/10/11 13:58
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public class OaCommonStatusEnum {

    public enum ApprovalStatus {
        STATUS_DRAFTING("drafting", "起草"),
        STATUS_APPROVAL("approval", "审批中"),
        STATUS_FINISH("finish", "审批完成");

        private String code;
        private String name;

        ApprovalStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getStatusApproval(String code) {
            for (ApprovalStatus approvalStatus : ApprovalStatus.values()) {
                if (approvalStatus.getCode().equals(code)) {
                    return approvalStatus.getName();
                }
            }
            return "";
        }

        public static String getApprovalCode(String name) {
            for (ApprovalStatus approvalStatus : ApprovalStatus.values()) {
                if (approvalStatus.getName().equals(name)) {
                    return approvalStatus.getCode();
                }
            }
            return "";
        }
    }

    public enum HasRead {
        STATUS_READ("read", "已阅"),
        STATUS_BEREAD("beread", "待阅");
        private String code;
        private String name;

        HasRead(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getHasRead(String code) {
            for (HasRead hasRead : HasRead.values()) {
                if (hasRead.getCode().equals(code)) {
                    return hasRead.getName();
                }
            }
            return "";
        }
    }

    public enum MonitorStatus {
        STATUS_ATTENTION("special_attention", "特别关注"),
        STATUS_WARN("warning", "预警"),
        STATUS_NORMAL("normal", "正常");

        private String code;
        private String name;

        MonitorStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getMonitorStatus(String code) {
            for (MonitorStatus monitorStatus : MonitorStatus.values()) {
                if (monitorStatus.getCode().equals(code)) {
                    return monitorStatus.getName();
                }
            }
            return "";
        }

        public static Map<String,String> toMapData(){
            Map<String,String> map=new HashMap<>(8);
            for (MonitorStatus monitorStatus : MonitorStatus.values()) {
                map.put(monitorStatus.getCode(),monitorStatus.getName());
            }
            return map;
        }
    }

    public enum AlertNode {
        STATUS_UNTREATED("untreated", "待办理"),
        STATUS_TRANSFER("transfer", "已转办"),
        STATUS_FEEDBACK("feedback", "已反馈"),
        STATUS_FINISH("finish", "已办结");

        private String code;
        private String name;

        AlertNode(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getAlertNode(String code) {
            for (AlertNode alertNode : AlertNode.values()) {
                if (alertNode.getCode().equals(code)) {
                    return alertNode.getName();
                }
            }
            return "";
        }

        public static boolean isExist(String code){
            for (AlertNode alertNode : AlertNode.values()) {
                if (alertNode.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }
    }
}
