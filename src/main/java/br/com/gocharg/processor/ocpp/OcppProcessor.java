package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.*;
import br.com.gocharg.dto.ocpp.json.response.*;
import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import br.com.gocharg.processor.ocpp.down.response.RemoteStartTransactionResponseProcessor;
import br.com.gocharg.processor.ocpp.up.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OcppProcessor implements CommandProcessor {

  @Autowired private BootNotificationProcessor bootNotificationProcessor;
  @Autowired private StatusNotificationProcessor statusNotificationProcessor;
  @Autowired private HeartbeatProcessor heartbeatProcessor;
  @Autowired private StartTransactionProcessor startTransactionProcessor;
  @Autowired private MeterValueProcessor meterValueProcessor;
  @Autowired private OcppResponseProcessor ocppResponseProcessor;

  @Autowired
  private RemoteStartTransactionResponseProcessor remoteStartTransactionResponseProcessor;

  @Autowired private OcppLogProcessor ocppLogProcessor;

  private static final Integer REQUEST_CODE = 2;

  @Override
  public Object process(CommandContext context) {
    Object[] ocppMessage = context.getProperty("ocppMessage", Object[].class);

    if (ocppMessage[0].equals(REQUEST_CODE)) {
      return processaRequest(ocppMessage, context);
    } else {
      processaResponse(ocppMessage, context);
      return null;
    }
  }

  private void processaResponse(Object[] ocppMessage, CommandContext context) {
    OcppResponse ocppResponse = new OcppResponse();
    ObjectMapper mapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    ocppResponse.setOperation(OcppMessageTypeEnum.get(Integer.valueOf(ocppMessage[0].toString())));
    ocppResponse.setUniqueID(ocppMessage[1].toString());
    ocppResponse.setApelidoTotem(ocppMessage[3].toString());
    ocppResponse.setStatus(mapper.convertValue(ocppMessage[2], OcppStatusResponse.class));

    context.put("ocppResponse", ocppResponse);

    ocppResponseProcessor.process(context);
  }

  private String processaRequest(Object[] ocppMessage, CommandContext context) {
    OcppRequest ocppRequest = new OcppRequest();

    ocppRequest.setOperation(OcppMessageTypeEnum.get(Integer.valueOf(ocppMessage[0].toString())));
    ocppRequest.setUniqueId(Integer.valueOf(ocppMessage[1].toString()));
    ocppRequest.setAction(OcppFunctionsEnum.get(ocppMessage[2].toString()));
    ocppRequest.setApelidoTotem(ocppMessage[4].toString());

    ObjectMapper mapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String retorno = new String();

    switch (ocppRequest.getAction()) {
      case BOOT_NOTIFICATION:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], BootNotificationRequest.class));

        context.put("ocppRequest", ocppRequest);

        retorno = bootNotificationProcessor.process(context);

        break;
      case STATUS_NOTIFICATION:
        ocppRequest.setPayload(
            mapper.convertValue(ocppMessage[3], StatusNotificationRequest.class));

        context.put("ocppRequest", ocppRequest);

        retorno = statusNotificationProcessor.process(context);

        break;
      case HEART_BEAT:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], HeartbeatRequest.class));

        context.put("ocppRequest", ocppRequest);

        retorno = heartbeatProcessor.process(context);

        break;
      case AUTHORIZE:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], AuthorizeRequest.class));

        break;
      case START_TRANSACTION:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], StartTransactionRequest.class));

        context.put("ocppRequest", ocppRequest);

        retorno = startTransactionProcessor.process(context);

        break;
      case METER_VALUES:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], MeterValuesRequest.class));

        context.put("ocppRequest", ocppRequest);

        meterValueProcessor.process(context);

        break;
      case STOP_TRANSACTION:
        ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], StopTransactionRequest.class));

        context.put("ocppRequest", ocppRequest);

        break;
      case FIRMWARE_STATUS_NOTIFICATION:
        ocppRequest.setPayload(
            mapper.convertValue(ocppMessage[3], FirmwareStatusNotificationRequest.class));

        break;
      case DIAGNOSTICS_STATUS_NOTIFICATION:
        ocppRequest.setPayload(
            mapper.convertValue(ocppMessage[3], DiagnosticsStatusNotificationRequest.class));

        break;
      case REMOTE_START_TRANSACTION:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], RemoteStartTransactionResponse.class));

          context.put("ocppRequest", ocppRequest);

          remoteStartTransactionResponseProcessor.process(context);
        }

        break;
      case REMOTE_STOP_TRANSACTION:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], RemoteStopTransactionResponse.class));
        }

        break;
      case RESERVE_NOW:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ReserveNowResponse.class));
        }

        break;
      case CANCEL_RESERVATION:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], CancelReservationResponse.class));
        }

        break;
      case CHANGE_CONFIGURATION:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ChangeConfigurationResponse.class));
        }

        break;
      case GET_CONFIGURATION:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], GetConfigurationResponse.class));
        }

        break;
      case UPDATE_FIRMWARE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], UpdateFirmwareResponse.class));
        }

        break;
      case GET_DIAGNOSTICS:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], GetDiagnosticsResponse.class));
        }

        break;
      case CHANGE_AVAILABILITY:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ChangeAvailabilityResponse.class));
        }

        break;
      case CLEAR_CACHE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ClearCacheResponse.class));
        }

        break;
      case RESET:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ResetResponse.class));
        }

        break;
      case UNLOCK_CONNECTOR:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], ResetResponse.class));
        }

        break;
      case SET_CHARGING_PROFILE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], SetChargingProfileResponse.class));
        }

        break;
      case GET_COMPOSITE_SCHEDULE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], GetCompositeScheduleResponse.class));
        }

        break;
      case CLEAR_CHARGING_PROFILE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(
              mapper.convertValue(ocppMessage[3], ClearChargingProfileResponse.class));
        }

        break;
      case DATA_TRANSFER:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], DataTransferResponse.class));
        }

        break;
      case TRIGGER_MESSAGE:
        if (ocppRequest.getOperation().equals(OcppMessageTypeEnum.CALL_RESULT)) {
          ocppRequest.setPayload(mapper.convertValue(ocppMessage[3], TriggerMessageResponse.class));
        }

        break;
    }
    ocppLogProcessor.process(context);

    return retorno;
  }
}
