public class studentAI extends Player {

    private int maxDepth;


    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void move(BoardState state) {
        move = alphabetaSearch(state, maxDepth);
    }

    public int alphabetaSearch(BoardState state, int maxDepth) {

        int v = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int move = -1;

        for (int i = 0; i < 6; i++) {
            if (state.isLegalMove(1, i)) {
                BoardState currState = state.applyMove(1, i);
                v = Math.max(v, minValue(currState, maxDepth, maxDepth-1, alpha, beta));
                if (v > alpha) {
                    move = i;
                    alpha = v;
                }
            }
        }
        return move;
    }

    public int maxValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {

        if (currentDepth == 0 ) {
            return sbe(state);
        }



            int v = Integer.MIN_VALUE;
           
         
            if(moveLegal(state,1)){
           for(int i = 0;i<6;i++) {
                if (state.isLegalMove(1, i)) {
                   BoardState temp = state.applyMove(1, i);
                    v = Math.max(v, minValue(temp, maxDepth, currentDepth - 1, alpha, beta));
                    if (v >= beta) {
                        return v;
                    }
                    alpha = Math.max(alpha, v);

                }
}

        } else {
                return sbe(state);
        }
            return v;



    }

    public int minValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
        if (currentDepth == 0 ) {
            return sbe(state);
        }


            int value = Integer.MAX_VALUE;
            BoardState temp;
       

        if(moveLegal(state,2)){

              for (int i = 0;i<6;i++)   {
                    if (state.isLegalMove(2, i)) {
                        temp = state.applyMove(2, i);
                        value = Math.min(value, maxValue(temp, maxDepth, currentDepth - 1, alpha, beta));
                        if (value <= alpha) {
                            return value;
                        }
                        beta = Math.min(beta, value);

                    }


                }

        } else {
            return sbe(state);
        }


            return value;



    }

    public int sbe(BoardState state){


        return  state.score[0] - state.score[1];
    }

    private boolean moveLegal (BoardState state, int player) {
       					 if (
                		state.isLegalMove(player,1)||
                        state.isLegalMove(player,2)||
                        state.isLegalMove(player,3)||
                        state.isLegalMove(player,4)||
                        state.isLegalMove(player,5)||
                        state.isLegalMove(player,6)) {
            return true;

        }

        return false;

    }


}