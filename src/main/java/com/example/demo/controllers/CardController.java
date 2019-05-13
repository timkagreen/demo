package com.example.demo.controllers;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/addCard")
    public String cardInfo(
            @RequestParam(name = "card_id", required = false, defaultValue = "69") String card_id,
            Map<String, Object> model
    ){
        model.put("card_id", card_id);
        return "addCard";
    }

    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<Card> cards = cardRepository.findAll();

        model.put("cards", cards);
        return "main";
    }

    @PostMapping
    public String addCard(@RequestParam Integer card_id,@RequestParam String password, Map<String, Object> model){
        Card card = new Card(card_id, password);
        cardRepository.save(card);
        Iterable<Card> cards = cardRepository.findAll();

        model.put("cards", cards);
        return "main";
    }
}
