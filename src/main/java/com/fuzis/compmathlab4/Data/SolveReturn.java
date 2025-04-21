package com.fuzis.compmathlab4.Data;

import com.fuzis.compmathlab4.Messaging.Transfer.GraphRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public record SolveReturn(GraphRequest graphRequest) {
}
