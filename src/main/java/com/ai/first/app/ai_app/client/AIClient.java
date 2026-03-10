package com.ai.first.app.ai_app.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class AIClient {
    // This class can be used for business logic related to AI operations
    // For example, you could add methods to interact with OpenAI's API here
    
    private final ChatClient chatClient;

    public AIClient(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String promptString) {
        return chatClient.prompt(promptString).call().content();
    }
}