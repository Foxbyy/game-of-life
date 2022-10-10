package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;
import com.gbtec.gameoflife.implementation.MaxVersion.gameRules;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void init(){
        // @formatter:off
        // Instead of "int[][]" you can also use "boolean[][]"
        int[][] generationData = {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        // @formatter:on
        for (int c = 0; c < 40; c++) {
            drawGeneration(generationData);         // printing the grid
            generationData = gameRules.checkRules(generationData); // checking the rules for next generation
            if (c < 39) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        drawGeneration(generationData);

        // @formatter:off
        /* To generate random values, you can use the class "java.util.Random". This class is providing two functions
         * which are really handy for our case: "nextBoolean" and "nextInt".
         * - "nextBoolean" will give you randomly "true" or "false"
         * - "nextInt" will give you a random int number. To limit te numbers given by this method to "0" and "1"
         *   you must set the origin to 0 and the bound to 2 like "nextInt(0, 2)"
         */
        // @formatter:on
    }
}
