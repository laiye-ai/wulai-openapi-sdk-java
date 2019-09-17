import exceptions.ClientException;
import exceptions.ServerException;
import module.request.msg.BotResponseRequest;
import module.request.msg.HistoryRequest;
import module.request.msg.ReceiveRequest;
import module.request.msg.SyncRequest;
import module.response.msg.*;

public interface IMsg {

    BotResponse getBotResponse(BotResponseRequest botResponseRequest)
            throws ClientException, ServerException;

    KeywordResponse getKeywordBotResponse(BotResponseRequest botResponseRequest)
            throws ClientException, ServerException;

    QaResponse getQABotResponse(BotResponseRequest botResponseRequest)
            throws ClientException, ServerException;

    TaskResponse getTaskBotResponse(BotResponseRequest botResponseRequest)
            throws ClientException, ServerException;

    HistoryResponse msgHistory(HistoryRequest historyRequest)
            throws ClientException, ServerException;

    ReceiveResponse msgReceive(ReceiveRequest receiveRequest)
            throws ClientException, ServerException;

    SyncResponse msgSync(SyncRequest syncRequest)
            throws ClientException, ServerException;
}
