package com.goods.app.vo;

public class Paging {
	public static final int PAGE_SCALE = 10;  //한 페이지에 보여지는 글의 수
	public static final int BLOCK_SCALE = 5;  // 한번에 보여지는 페이지넘버의 개수
	
	private int curPage;   //현재 페이지
    private int prevPage;  //이전 페이지
    private int nextPage;   //다음 페이지
    private int totalPage;   //전체 페이지 수
    private int totalBlock;   //전체 블럭의 수  에를들면 총 10페이지 인 경우, 블럭은 2개 (5페이지씩 나타낼 것이기 때문)
    private int curBlock;    // 현재 블럭
    private int prevBlock;   // 이전 블럭
    private int nextBlock;   // 다음 블럭
    
    private int pageBegin;    // 블럭 내에 첫페이지 넘버
    private int pageEnd;    //  블럭 내에 마지막 페이지 넘버
    
    private int blockBegin; 
    private int blockEnd; 
    
//글 20개 ->  1, 2 페이지  두개 나옴 블럭은 1개
//1페이지일 경우 1  2페이지일 경우 11(10 + 1)
   
 

	public Paging(int count, int curPage) {
        curBlock = 1; 
        this.curPage = curPage; 
        setTotalPage(count); 
        setPageRange(); 
        setTotalBlock(); 
        setBlockRange(); 
      
    }
    

    public void setBlockRange(){

        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;

        blockBegin = (curBlock-1)*BLOCK_SCALE+1;   

        blockEnd = blockBegin+BLOCK_SCALE-1;

        if(blockEnd > totalPage) blockEnd = totalPage;

        prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;

        nextPage = curBlock > totalBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;

        if(nextPage >= totalPage) nextPage = totalPage;
    }
    
    public void setPageRange(){
        pageBegin = (curPage-1)*PAGE_SCALE+1;
        pageEnd = pageBegin+PAGE_SCALE-1;
    }
    
    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getPrevPage() {
        return prevPage;
    }
    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int count) {
        totalPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
    }
    public int getTotalBlock() {
        return totalBlock;
    }
    public void setTotalBlock() {
        totalBlock = (int)Math.ceil(totalPage / BLOCK_SCALE);
    }
    
    public int getCurBlock() {
        return curBlock;
    }
    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }
    public int getPrevBlock() {
        return prevBlock;
    }
    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }
    public int getNextBlock() {
        return nextBlock;
    }
    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    public int getPageBegin() {
        return pageBegin;
    }
    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }
    public int getPageEnd() {
        return pageEnd;
    }
    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    public int getBlockBegin() {
        return blockBegin;
    }
    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }
    public int getBlockEnd() {
        return blockEnd;
    }
    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
} 