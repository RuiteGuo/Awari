import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedList;

public class SBAI extends Player {
    private int maxDepth;

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void move(BoardState state) {
        move = alphabetaSearch(state, maxDepth);
    }

    public int alphabetaSearch(BoardState state, int maxDepth) {
        //Store all possible moves in a linkedList
        LinkedList<Integer> legalMove = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            if (state.isLegalMove(1, i)) {
                legalMove.add(i);
            }
        }

        int bestMove = -1;
        int v = Integer.MIN_VALUE;
        int maxV = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        for (int i : legalMove) {
            //get maximum value associated with that move
            v = Math.max(v, minValue(state.applyMove(1, i), 0, maxDepth - 1, alpha, beta));
            //compare and switch the best move so far
            if (v > maxV) {
                maxV = v;
                bestMove = i;
            }
        }
        return bestMove;
    }

    public int maxValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
        if (currentDepth == 0)
            return sbe(state);
        boolean terminate = true;
        LinkedList<Integer> legalMove = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            if (state.isLegalMove(1, i)) {
                terminate = false;
                legalMove.add(i);
            }
        }
        if (terminate)
            return sbe(state);

        int v = Integer.MIN_VALUE;
        for (int i : legalMove) {
            v = Math.max(v, minValue(state.applyMove(1, i), 0, currentDepth - 1, alpha, beta));
            if (v >= beta)
                return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }

    public int minValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
        if (currentDepth == 0)
            return sbe(state);
        boolean terminate = true;
        LinkedList<Integer> legalMove = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            if (state.isLegalMove(2, i)) {
                terminate = false;
                legalMove.add(i);
            }
        }
        if (terminate)
            return sbe(state);

        int v = Integer.MAX_VALUE;
        for (int i : legalMove) {
            v = Math.min(v, maxValue(state.applyMove(2, i), 0, currentDepth - 1, alpha, beta));
            if (v <= alpha)
                return v;
            beta = Math.min(beta, v);
        }
        return v;
    }

    public int sbe(BoardState state) {
        return state.getMyScore(1) - state.getMyScore(2);
    }


}