package com.dj.springboot;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Open AI的api调用示例
 */
class OpenAiApiExample {
    public static void main(String... args) {
        String token = "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";
        OpenAiService service = new OpenAiService(token);

        // 补全文本
//        System.out.println("\nCreating completion...");
//        CompletionRequest completionRequest = CompletionRequest.builder()
//                .model("ada")
//                .prompt("Somebody once told me the world is gonna roll me")
//                .echo(true)
//                .user("testing")
//                .n(3)
//                .build();
//        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
//
        // 生成图片
//        System.out.println("\nCreating Image...");
//        CreateImageRequest request = CreateImageRequest.builder()
//                .prompt("一个漂亮的女孩在海边散步")
//                .build();
//
//        System.out.println("\nImage is located at:");
//        System.out.println(service.createImage(request).getData().get(0).getUrl());

        // chatgpt
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage("assistant", "今天头有点晕，安慰我下呗！");
        messages.add(chatMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
//                .model("gpt-4-0314")
                .messages(messages)
                .user("MeMo")
                .temperature(0.8)
//                .n(2)
                .build();
        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(System.out::println);
//        service.createCompletion((CompletionRequest)chatCompletionRequest).getChoices()
//        service.createCompletion(chatCompletionRequest).getChoices().forEach(System.out::println);
    }
}
