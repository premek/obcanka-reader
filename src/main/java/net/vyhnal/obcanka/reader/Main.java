/*
 * Copyright 2019 Paralelni Polis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.vyhnal.obcanka.reader;

import com.google.gson.Gson;
import cz.paralelnipolis.obcanka.core.HexUtils;
import cz.paralelnipolis.obcanka.core.card.Card;
import cz.paralelnipolis.obcanka.core.certificates.Certificate;
import cz.paralelnipolis.obcanka.core.communication.CardException;
import cz.paralelnipolis.obcanka.desktop.lib.DesktopCardInterface;

import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                CardTerminal terminal = Main.getCardTerminalWithCard();
                DesktopCardInterface ci = DesktopCardInterface.create(terminal.connect("T=0"));
                Card cm = new Card(ci);
                System.out.println(new Gson().toJson(new CardData(cm)));
                terminal.waitForCardAbsent(0);
            } catch (Exception e) {
                sleep(3000);
                e.printStackTrace();
            }
        }
    }


    public static CardTerminal getCardTerminalWithCard() throws javax.smartcardio.CardException, IOException {

        TerminalFactory factory = TerminalFactory.getDefault();
        CardTerminals terminals = factory.terminals();

        List<CardTerminal> allTerminals = terminals.list();
        if (allTerminals.isEmpty()) {
            throw new IOException("No CardTerminal seems to be inserted.");
        }
        List<CardTerminal> terminalsWithCard = terminals.list(CardTerminals.State.CARD_PRESENT);
        if (terminalsWithCard.isEmpty()) {
            while (terminalsWithCard.isEmpty()) {
                terminals.waitForChange(1000);
                terminalsWithCard = terminals.list(CardTerminals.State.CARD_PRESENT);
            }
        }
        return terminalsWithCard.get(0);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e1) {
        }
    }
}
