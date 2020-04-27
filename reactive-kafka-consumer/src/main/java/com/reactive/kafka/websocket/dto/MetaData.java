package com.reactive.kafka.websocket.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "MetaDataBuilder", toBuilder = true)
@JsonDeserialize(builder = MetaData.MetaDataBuilder.class)
public class MetaData {

    String affectedNode;
    String affectedEquipment;
    String affectedSite;
    String alarmCategory;
    String alarmGroup;
    String alarmCSN;
    String alarmID;
    String alarmMO;
    String alarmNotificationType;
    String alarmLastSeqNo;
    String alarmEventTime;
    String vnocAlarmID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MetaDataBuilder {
    }
}


