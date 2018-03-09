public class GHYAI extends Player {
    private int maxDepth;


    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void move(BoardState state) {
        move = alphabetaSearch(state, maxDepth);
    }

    public int alphabetaSearch(BoardState state, int maxDepth) {
    	int move = -1;
    	int alpha = Integer.MIN_VALUE;
    	int beta = Integer.MAX_VALUE;
    	
    	int v = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < 6; ++i) {
    		if (state.isLegalMove(1, i)) {
    			v = Math.max(v,minValue(state.applyMove(1,i),maxDepth,maxDepth-1,alpha,beta));
    			if (v > alpha) {
    				move = i;
    				alpha = v;
    			}
    		}
    	}
    	return move;
    }

    public int maxValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
    	if (currentDepth == 0) {
    		return sbe(state);
    	}
    	
    	int v = Integer.MIN_VALUE;
    	
    	boolean isLeaf = true; 
    	for (int i = 0; i < 6; ++i) {
    		if (state.isLegalMove(1, i)) {
    			isLeaf = false;
    			v = Math.max(v,minValue(state.applyMove(1,i),maxDepth,currentDepth-1,alpha,beta));
    			if (v >= beta) {
    				return v;
    			}
    			alpha = Math.max(alpha,v);
    		}
    	}
    	
    	if (isLeaf) {
    		return sbe(state);
    	}
    	
    	return v;
    	
    }

    public int minValue(BoardState state, int maxDepth, int currentDepth, int alpha, int beta) {
    	if (currentDepth == 0) {
    		return sbe(state);
    	}
    	
    	int v = Integer.MAX_VALUE;
    	
    	boolean isLeaf = true; 
    	for (int i = 0; i < 6; ++i) {
    		if (state.isLegalMove(2, i)) {
    			isLeaf = false;
    			v = Math.min(v,maxValue(state.applyMove(2,i),maxDepth,currentDepth-1,alpha,beta));
    			if (alpha >= v) {
    				return v;
    			}
    			beta = Math.min(beta,v);
    		}
    	}
    	
    	if (isLeaf) {
    		return sbe(state);
    	}
    	
    	return v;
    }

    public int sbe(BoardState state){
    	return state.score[0] - state.score[1];
    }


}