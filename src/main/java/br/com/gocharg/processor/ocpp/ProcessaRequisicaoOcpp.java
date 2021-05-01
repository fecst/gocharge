package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.*;
import br.com.gocharg.dto.ocpp.json.response.*;
import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessaRequisicaoOcpp implements CommandProcessor {

  @Autowired private ProcessaBootNotificationProcessor bootNotificationProcessor;

  @Override
  public Object process(CommandContext context) {
    try {
      Object[] ocppMessage = context.getProperty("ocppMessage", Object[].class);
      OcppRequest ocppRequest = new OcppRequest();

      ocppRequest.setOperation(OcppMessageTypeEnum.get(Integer.valueOf(ocppMessage[0].toString())));
      ocppRequest.setUniqueID(ocppMessage[1].toString());
      ocppRequest.setAction(OcppFunctionsEnum.get(ocppMessage[2].toString()));

      ObjectMapper mapper =
          new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      String retorno = new String();

      switch (ocppRequest.getAction()) {
          //      UP - Chamadas do EV
        case BOOT_NOTIFICATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], BootNotificationRequest.class));

          context.put("ocppRequest", ocppRequest);

          retorno = bootNotificationProcessor.process(context);
          break;
        case STATUS_NOTIFICATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], StatusNotificationRequest.class));
          break;
        case HEART_BEAT:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], HeartbeatRequest.class));
          break;
        case AUTHORIZE:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], AuthorizeRequest.class));
          break;
        case START_TRANSACTION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], StartTransactionRequest.class));
          break;
        case METER_VALUES:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], MeterValuesRequest.class));
          break;
        case STOP_TRANSACTION:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], StopTransactionRequest.class));
          break;
        case FIRMWARE_STATUS_NOTIFICATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], FirmwareStatusNotificationRequest.class));
          break;
        case DIAGNOSTICS_STATUS_NOTIFICATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], DiagnosticsStatusNotificationRequest.class));
          break;

          //      DOWN - Respostas do EV
        case REMOTE_START_TRANSACTION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], RemoteStartTransactionResponse.class));
          break;
        case REMOTE_STOP_TRANSACTION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], RemoteStopTransactionResponse.class));
          break;
        case RESERVE_NOW:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ReserveNowResponse.class));
          break;
        case CANCEL_RESERVATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], CancelReservationResponse.class));
          break;
        case CHANGE_CONFIGURATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ChangeConfigurationResponse.class));
          break;
        case GET_CONFIGURATION:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], GetConfigurationResponse.class));
          break;
        case UPDATE_FIRMWARE:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], UpdateFirmwareResponse.class));
          break;
        case GET_DIAGNOSTICS:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], GetDiagnosticsResponse.class));
          break;
        case CHANGE_AVAILABILITY:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ChangeAvailabilityResponse.class));
          break;
        case CLEAR_CACHE:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ClearCacheResponse.class));
          break;
        case RESET:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ResetResponse.class));
          break;
        case UNLOCK_CONNECTOR:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ResetResponse.class));
          break;
        case SET_CHARGING_PROFILE:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], SetChargingProfileResponse.class));
          break;
        case GET_COMPOSITE_SCHEDULE:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], GetCompositeScheduleResponse.class));
          break;
        case CLEAR_CHARGING_PROFILE:
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ClearChargingProfileResponse.class));
          break;
        case DATA_TRANSFER:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], DataTransferResponse.class));
          break;
        case TRIGGER_MESSAGE:
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], TriggerMessageResponse.class));
          break;
      }

      return retorno;
    } catch (Exception e) {
      System.out.println("Problema na conversão do Payload");
    }

    return null;
  }
}
