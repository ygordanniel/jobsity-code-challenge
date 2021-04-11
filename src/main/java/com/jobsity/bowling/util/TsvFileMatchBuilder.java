package com.jobsity.bowling.util;

import com.jobsity.bowling.match.Player;
import com.jobsity.bowling.roll.Roll;
import com.jobsity.bowling.roll.RollParser;
import com.jobsity.bowling.exception.InvalidMatchException;
import com.jobsity.bowling.frame.Frame;
import com.jobsity.bowling.frame.FrameParser;
import com.jobsity.bowling.match.CmdScoreBoard;
import com.jobsity.bowling.match.Match;
import com.jobsity.bowling.match.TenPinMatch;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is a specialization of @{@link MatchBuilder} interface to build a match from a TSV file.
 * File exemple:
 * <pre>{@code
 * Jeff    10
 * John    3
 * John    7
 * Jeff    7
 * Jeff    3
 * John    6
 * John    3
 * Jeff    9
 * Jeff    0
 * John    10
 * Jeff    10
 * John    8
 * John    1
 * Jeff    0
 * Jeff    8
 * John    10
 * Jeff    8
 * Jeff    2
 * John    10
 * Jeff    F
 * Jeff    6
 * John    9
 * John    0
 * Jeff    10
 * John    7
 * John    3
 * Jeff    10
 * John    4
 * John    4
 * Jeff    10
 * Jeff    8
 * Jeff    1
 * John    10
 * John    9
 * John    0
 * }</pre>
 */
@NoArgsConstructor
public class TsvFileMatchBuilder implements MatchBuilder<String> {

    private RollParser rollParser = new RollParser();
    private FrameParser frameParser = new FrameParser();
    private final int MIN_TEN_PIN_FRAMES = 10;
    private final int MAX_TEN_PIN_FRAMES = 12;

    public Match build(String file) throws FileNotFoundException {
        InputStream matchInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(matchInputStream));
        List<String> results = reader.lines().collect(Collectors.toList());
        Map<String, List<Roll>> playerAndRolls = new HashMap<>();
        results.forEach(result -> {
            String[] playerAndResult = result.split("\\s+");
            String player = playerAndResult[0];
            String roll = playerAndResult[1];
            List<Roll> rolls = playerAndRolls.get(player);
            if (Objects.isNull(rolls)) {
                rolls = new ArrayList<>();
                playerAndRolls.put(player, rolls);
            }
            rolls.add(new Roll(rollParser.parse(roll)));
        });

        return this.buildTenPinMatch(playerAndRolls);
    }

    private Match buildTenPinMatch(Map<String, List<Roll>> playerAndRolls) {
        Match match = new TenPinMatch(new CmdScoreBoard());
        List<Player> players = new ArrayList<>();
        playerAndRolls.forEach((key, value) -> {
            List<Frame> frames = frameParser.parse(value);
            players.add(new Player(key, frames, match.getMaxCalculableFrames()));
        });

        boolean isInvalidTenPinMatch = players.stream()
                .anyMatch(player -> player.getFrames().size() < MIN_TEN_PIN_FRAMES ||
                        player.getFrames().size() > MAX_TEN_PIN_FRAMES);

        if (isInvalidTenPinMatch) {
            throw new InvalidMatchException();
        }

        match.addPlayer(players);
        return match;
    }
}
