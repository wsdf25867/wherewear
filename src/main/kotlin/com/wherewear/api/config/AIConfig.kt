package com.wherewear.api.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AIConfig {

    @Bean
    fun chatClient(chatModel: ChatModel) = ChatClient.create(chatModel)
}
