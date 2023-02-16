//수열의 마지막 숫자를 구하라
public class Sequence {

    /**
     * 요구사항
     * 입력되는 수열이 등비인지 등차인지 구분하고 마지막에 올 숫자를 구하라
     * 입력되는 수열의 길이는 2< sequence.length <1000
     * 각 숫자는 정수 -1000 < num < 2000
     * 등비수열의 공비는 0이 아닌 정수이다
     */

    public int solution(int[] common) {
        int answer = 0;

        boolean isArithmetic = true; //등차수열여부
        int commonNum = 0; //공차 또는 공비

        if(common.length <= 2 || common.length >= 1000)
            return answer;

        for(int num : common) {
            if(num < -999 || num > 1999) return answer;
        }

        //등차수열 여부를 판단, 등차수열이 아니라면 무조건 등비수열
        for (int i = 0; i <common.length; i++) {

            if((i+2) >= common.length) break;

            //하나라도 공차가 같지 않다면 등차수열이 아니다
            if(common[i+1]-common[i] != common[i+2]-common[i+1]) {
                isArithmetic = false;
                break;
            }
        }

        if (isArithmetic){
            commonNum = common[1] - common[0]; //공차
            answer = common[common.length-1] + commonNum;
        } else {
            commonNum = common[1] / common[0];
            answer = common[common.length-1] * commonNum;
        }

        return answer;
    }

}
