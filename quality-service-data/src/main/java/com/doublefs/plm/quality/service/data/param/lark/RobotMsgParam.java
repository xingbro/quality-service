package com.doublefs.plm.quality.service.data.param.lark;

import lombok.*;

/**
 * @Description
 * @ClassName RobotMsgVO
 * @Author lijiawei
 * @date 2021.01.30 15:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RobotMsgParam {

    /**
     * 发送消息的类型
     */
    private String msg_type="text";

    /**
     * 发送消息的内容
     */
    private RobotMsgContent content;
}
