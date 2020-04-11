package io.qkits.walkthough.netty.adapter.api.serv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRuntime {
    private State state = State.up;

    public static enum State {
        up, down;
    }
}
