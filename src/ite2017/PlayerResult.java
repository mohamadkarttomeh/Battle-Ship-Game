package ite2017;
public class PlayerResult {
    int WinCount = 0;
    int LoseCount= 0;
    int GameCount= 0;
    int TotalMark= 0;
    double res= 0;
    double AverageMark= 0;
    String PlayerName= "";
    public void update()
    {
        res = ((double) WinCount) / ((double)GameCount);
        AverageMark = ((double) TotalMark) / ((double)GameCount);
    }
}
