import ballerina.lang.system;
import ballerina.lang.messages;
import ballerina.net.ws;
import ballerina.net.http;

@http:BasePath {value:"/chat"}
@ws:WebSocketUpgradePath {value:"/ws"}
service oddEvenWebSocketConnector {

    string evenConnectionGroupName = "evenGroup";
    string oddConnectionGroupName = "oddGroup";
    int i = 1;

    @ws:OnOpen {}
    resource onOpen(message m) {
        system:println("New client connected to the server.");
        system:println(i);
        if (i % 2 == 0) {
            ws:addConnectionToGroup(evenConnectionGroupName);
        } else {
            ws:addConnectionToGroup(oddConnectionGroupName);
        }
        i = i + 1;
    }

    @ws:OnTextMessage {}
    resource onTextMessage(message m) {
        ws:pushTextToGroup(oddConnectionGroupName, oddConnectionGroupName + ": " + messages:getStringPayload(m));
        ws:pushTextToGroup(evenConnectionGroupName, evenConnectionGroupName+ ": " + messages:getStringPayload(m));
    }

    @ws:OnClose {}
    resource onClose(message m) {
        system:println("client left the server.");
        ws:broadcastText("client left the server.");
    }

}
