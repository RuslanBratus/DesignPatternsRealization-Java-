package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new HashMap<>();
                List<Card> list = new LinkedList<>(deck.restCards());
                //System.out.println(deck.restCards());

                map.put("Community",list.subList(players*2,players*2+5));
                for(int i=1;i<=players;i++){
                    List<Card> playerCart =new LinkedList<>();
                    playerCart.add(list.get(i-1));
                    playerCart.add(list.get(players+i-1));
                    map.put("Player "+i,playerCart);
                }
                map.put("Remaining",list.subList(players*2+5,list.size()));
                return map;
            }
        };

    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new HashMap<>();
                List<Card> list = new LinkedList<>(deck.restCards());

                for (int i = 1; i <= players; i++) {
                    List<Card> playerCart = new LinkedList<>();
                    for (int k = 0; k < 5; k++)
                    {
                        playerCart.add(list.get(i - 1 + k*players));
                    }

                    map.put("Player " + i, playerCart);
                }
                map.put("Remaining", list.subList(players * 5, list.size()));
                return map;
            }


        };
    };

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new HashMap<>();
                List<Card> list = new LinkedList<>(deck.restCards());

                for (int i = 0; i < players; i++) {
                    List<Card> playerCart = new LinkedList<>();
                    for (int k = 0; k < list.size() / players; k++)
                    {
                        playerCart.add(list.get(i + k * players));
                    }

                    map.put("Player " + (i+1), playerCart);
                }
                //  map.put("Remaining", list.subList(players * 5, list.size()));
                return map;
            }
        };

    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new HashMap<>();
                List<Card> list = new LinkedList<>(deck.restCards());

                for (int i = 0; i < players; i++) {
                    List<Card> playerCart = new LinkedList<>();
                    for (int k = 0; k < 6; k++)
                    {
                        playerCart.add(list.get(i + k * players));
                    }

                    map.put("Player " + (i+1), playerCart);
                }
                //asdasdasd
                map.put("Remaining", list.subList((players * 6) + 1, list.size()));
                map.put("Trump card", list.subList(players * 6, players * 6+1));
                return map;
            }
        };

    }

}
