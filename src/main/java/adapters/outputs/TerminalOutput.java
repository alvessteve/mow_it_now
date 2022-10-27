package adapters.outputs;

import domain.model.mow.Mow;
import ports.Displayer;

import java.util.Collection;

public class TerminalOutput implements Displayer {

    @Override
    public void mowsPosition(Collection<Mow> mows) {
        StringBuilder sb = new StringBuilder();
        mows.forEach(mow -> {
            sb.append(mow.toString());
            sb.append("\n");
        });
        System.out.println(sb);
    }

}
