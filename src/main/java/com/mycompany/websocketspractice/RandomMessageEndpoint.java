/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocketspractice;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mdeboer1
 */
@ServerEndpoint(value="/notify")
public class RandomMessageEndpoint {
    private static final Set<Session> USER_SESSIONS =
            Collections.synchronizedSet(new HashSet<Session>());
    private static String[] words = {"Hello there", "Goodbye", "Something else",
        "Another one", "Exit"};
        
    @OnOpen
    public void onOpen(Session userSession){
        USER_SESSIONS.add(userSession);
    }
    
    @OnClose
    public void onClose(Session userSession){
        USER_SESSIONS.remove(userSession);
    }
    
    @OnMessage
    public void onMessage(String m, Session userSession) throws IOException{
        Random random = new Random();
        int rn = random.nextInt(5);
        String message = words[rn];
        for (Session session : USER_SESSIONS){
            session.getBasicRemote().sendText(message);
        }
            
    }
}
