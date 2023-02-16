import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class BrandiTest {

    //첫번째 알고리즘 테스트, 총 판매된 티켓 수량 구하기
    public int solution1(int tickets, int[][]requests){
        //이건 정렬 문제
        int answer = 0;

        if(tickets < 1 || tickets > 1000000 || requests.length < 1 || requests.length > 1000000)
            return answer;

        //등급 정렬(내림차순), 구매티켓수 (내림차순)
        Arrays.sort(requests, (o1, o2)-> {
            return o1[0] != o2[0] ? o1[0]-o2[0] : o2[1]-o1[1];
        });

        for(int i=0; i< requests.length; i++){
            System.out.println(requests[i][0]+","+requests[i][1]+","+tickets);

            //남은 티켓보다 넘게 사려는 사람은 스킵
            if(requests[i][1] <= tickets) {
                answer += requests[i][1];
                tickets -= requests[i][1];
            }

            if(tickets < 1) break;
        }

        System.out.println(answer);

        return answer;
    }

    public int solution2(int n, int m, int[] points, String[] hands)  {
        //문자열 그룹핑
        int answer = 0;

        int[][] scoreboard = new int[n][m];

        int sCount = 0;
        int rCount = 0;
        int pcount = 0;

        String[] playerHands = {};

        for (int i = 0; i < 5; i++) {
            playerHands =  hands[i].split("");

            List<String> shapes = Arrays.asList(playerHands).stream().distinct().collect(Collectors.toList());
            System.out.println("이번판 모양 수: "+shapes.size());


            //모양이 한가지인 경우 이번판 중단, 점수 이월
            if(shapes.size() == 1) {
                points[i+1] += points[i];
                continue;
            } else if(shapes.size() == 2){ //모양이 두가지인 경우
                System.out.println(this.getGameResult(shapes.get(0), shapes.get(1)));
                //점수가 양수인 경우
                if(points[i] > 0) {
                    //이긴 사람에게 점수를 더한다
                    //이긴 모양을 찾는다
                    //이긴 모양의 인덱스를 찾는다
                    //이긴 모양의 인덱스에 점수를 더 한다
                } else {
                    //음수일때 진사람에게 점수를 더한다
                    //진 모양을 찾는다
                    //진 모양의 인덱스를 찾는다
                    //진 모양의 인덱스에 점수를 더 한다
                }
            } else if(shapes.size() == 3){ //모양이 세가지인 경우
                sCount = CountHands("S", playerHands);
                rCount = CountHands("R", playerHands);
                pcount = CountHands("P", playerHands);

                //세 그룹의  크기가 같은 경우 이번라운드 스킵, 점수 이월
                //두 그룹의 크기가 같은 경우
            }




        }

        //모든 모양이 한가지인 경우 다음 라운드로 넘어가고 점수는 이월된다


        System.out.println(hands[1]);


        return answer;
    }


    public int CountHands(String handShape, String[] hands) {
        int count = 0;
        for(String h : hands) {
            if(handShape.equals(h)) count++;
        }
        return count;
    }

    //브랜드 코테 2번 다시 푼다
    /* 요구사항 분석
    * S:가위, R:바위, P:보
    * n명, m번의 라운드 스코어보드 있어야 함
    * 매 라운드 같은 모양을 낸 사람은 한 팀
    * 참가자가 낸 모양에 따라 분기
    * 모양이 하나, 둘, 세가지의 경우
    * 한가지, 라운드 스킵 점수는 이월
    * 두가지, 라운드 점수 양수일때 이긴 팀 점수 겟, 음수일때 진 팀 점수 겟
    * 세가지
    *   세팀 크기가 같을때 라운드 스킵, 점수 이월
    *   두 그룹 크기가 같을때 크기가 다름 한팀이 점수 겟
    *   그룹 크기가 모두 다를때
    *      라운드 점수가 양수일때, 그룹 크기가 가장 큰 팀 제외, 나머지 팀중 이긴 그룹이 점수 겟
    *       라운드 점수가 음수일때, 그룹 크기가 가장 작은 팀 제외 나머지 팀중 진 그룹 점수 겟
    * 마지막 라운드가 스킵될 때 해당 라운드 점수는 사라짐
    *
    * */

    /*필요 기능
    * 가위바위보 게임
    * 참가자 스코어보드
    * 매라운드 그룹핑하기
    * 매라운드 각 그룹(팀) 크기 구하기
    * 매라운드 스코어보드 입력
    * 매라운드 모양별 참가자 그룹핑하고 크기 구하기
    * 매라운드 참가자가 낸 모양을 기억하고 게임 결과 점수를 해당 인덱스에 더 한다
    * */

    //가위바위보
    public enum GawiBawiBO {
        S("S", 1, "가위"), R("R", 2, "바위"), P("P", 3, "보");

        private final String name;
        private final int shapeNum;
        private final String korName;

        GawiBawiBO(String name, int num, String korName) {
            this.name = name;
            this.shapeNum = num;
            this.korName = korName;
        }

        public int getValue() {
            return shapeNum;
        }

        public String getName(){
            return name;
        }

        public String getKorName() {
            return korName;
        }
    }

    /*
     * * 모양이 하나, 둘, 세가지의 경우
     * 한가지, 라운드 스킵 점수는 이월
     * 두가지, 라운드 점수 양수일때 이긴 팀 점수 겟, 음수일때 진 팀 점수 겟
     * 세가지
     *   세팀 크기가 같을때 라운드 스킵, 점수 이월
     *   두 그룹 크기가 같을때 크기가 다름 한팀이 점수 겟
     *   그룹 크기가 모두 다를때
     *      라운드 점수가 양수일때, 그룹 크기가 가장 큰 팀 제외, 나머지 팀중 이긴 그룹이 점수 겟
     *       라운드 점수가 음수일때, 그룹 크기가 가장 작은 팀 제외 나머지 팀중 진 그룹 점수 겟
     * */
    public void solution2_1(int n, int m, int[] points, String[] hands) {
        //가위(S,1), 바위(R,2), 보(P,3) 게임 테스트
        System.out.println("가위의 값: "+GawiBawiBO.S.getName()+"("+GawiBawiBO.S.getValue()+")");
        System.out.println("바위의 값: "+GawiBawiBO.R.getName()+"("+GawiBawiBO.R.getValue()+")");
        System.out.println("보위의 값: "+GawiBawiBO.P.getName()+"("+GawiBawiBO.P.getValue()+")");

        String[] shapePerRound;
        Map<String, String> gameResult;
        int shapesCntPerRound = 0;


        //스코어보드
        List<int[]> scoreboard = new ArrayList<int[]>();

        //게임 라운딩
        for (int i = 0; i < m; i++) {
            //이번판에 나온 모양의 개수
            shapePerRound = Arrays.stream(hands[i].split("")).distinct().toArray(String[]::new);
            shapesCntPerRound = shapePerRound.length;

            System.out.println(hands[i]+" 등장 모양은 몇개 ? "+shapesCntPerRound);

            if(shapesCntPerRound == 1) { //모양이 한가지 일때 라운드 스킵, 점수 이월
                if(i != m-1) points[i+1] += points[i]; //마지막 라운드가 아니라면 이월
                continue;
            } else if (shapesCntPerRound == 2) {
                //승리팀 뽑기
                gameResult = this.getGameResult(shapePerRound[0], shapePerRound[1]);
                if(points[i] > 0 ) { //점수가 양수일때 이긴팀 점수 겟
                    scoreboard.add(this.scoring(n, hands[i], gameResult.get("win"), points[i]));
                } else { //점수가 음수일때 진팀 점수 겟
                    scoreboard.add(this.scoring(n, hands[i], gameResult.get("lose"), points[i]));
                }
            } else if (shapesCntPerRound == 3) {
                //팀원 수 세기
                int sCount = (int) hands[i].chars().filter(c -> c == 'S').count();
                int rCount = (int) hands[i].chars().filter(c -> c == 'R').count();
                int pCount = (int) hands[i].chars().filter(c -> c == 'P').count();

                if(sCount == rCount && rCount == pCount) { //세팀 크기가 같을때, 점수 이월
                    if(i != m-1) points[i+1] += points[i]; //마지막 라운드가 아니라면 이월
                    continue;
                } else if (sCount == rCount ? (sCount != pCount ? true : false) : (sCount == pCount ? true : (rCount == pCount ? true : false))) { //두팀 크기가 같을때
                    //중복 숫자 제거
                    if(sCount == rCount) {
                        scoreboard.add(this.scoring(n, hands[i], "P", points[i]));
                    } else if (rCount == pCount) {
                        scoreboard.add(this.scoring(n, hands[i], "S", points[i]));
                    } else if (pCount == sCount) {
                        scoreboard.add(this.scoring(n, hands[i], "R", points[i]));
                    }
                } else if (sCount != rCount && rCount != pCount || pCount != sCount) { //그룹 크기가 모두 다를때
                    if(points[i] > 0 ) { //점수가 양수일때
                        //크기가 가장 큰 팀 제외
                        //나머지 두팀 중 이긴팀 점수 겟
                        if(sCount > rCount && sCount > pCount){ //S가 제일 큰 팀
                            gameResult = this.getGameResult(GawiBawiBO.R.getName(), GawiBawiBO.P.getName());
                        } else if (rCount > sCount && rCount > pCount) { //R이 제일 큰팀
                            gameResult = this.getGameResult(GawiBawiBO.S.getName(), GawiBawiBO.P.getName());
                        } else { //P가 제일 큰팀
                            gameResult = this.getGameResult(GawiBawiBO.S.getName(), GawiBawiBO.R.getName());
                        }
                        scoreboard.add(this.scoring(n, hands[i], gameResult.get("win"), points[i]));
                    } else {
                        //크기가 가장 작은 팀 제외
                        //나머지 두팀 중 진팀 점수 겟
                        if(sCount < rCount && sCount < pCount){ //S가 작은 큰 팀
                            gameResult = this.getGameResult(GawiBawiBO.R.getName(), GawiBawiBO.P.getName());
                        } else if (rCount < sCount && rCount < pCount) { //R이 작은 큰팀
                            gameResult = this.getGameResult(GawiBawiBO.S.getName(), GawiBawiBO.P.getName());
                        } else { //P가 작은 큰팀
                            gameResult = this.getGameResult(GawiBawiBO.S.getName(), GawiBawiBO.R.getName());
                        }
                        scoreboard.add(this.scoring(n, hands[i], gameResult.get("lose"), points[i]));
                    }
                }

            }

        }
        //인덱스별로 계산 해야겠지?
        int[] finalScore = new int[n];
        for (int[] score : scoreboard) {
            for (int i = 0; i < n; i++) {
                finalScore[i] += score[i];
            }
        }

        System.out.println(Arrays.stream(finalScore).max().getAsInt());
    }

    /*\
    * 모양이 같다면 게임을 돌리지 않는다
    * 따라서 승패만 있고 비기는건 없다
    * 게임 경우의 수(비김 제외) : 21,31,32
     */
    public String getWinShape(String shape1, String shape2) { //이긴 모양만 출력
        String winShape = "";
        int shapeNum1 = GawiBawiBO.valueOf(shape1).getValue();
        int shapeNum2 = GawiBawiBO.valueOf(shape2).getValue();

        switch (shapeNum1*shapeNum2)  {
            case 2 : case 6 : //가위(1)vs바위(2), 바위(2)vs보(3), 큰 숫자가 위너
                winShape = shapeNum1 > shapeNum2 ? GawiBawiBO.valueOf(shape1).getName() : GawiBawiBO.valueOf(shape2).getName();
                break;
            case 3 : //가위(1)vs보(3), 작은 숫자가 위너
                winShape = shapeNum1 < shapeNum2 ? GawiBawiBO.valueOf(shape1).getName() : GawiBawiBO.valueOf(shape2).getName();
                break;
        }
        return winShape;
    }
    public Map<String,String> getGameResult(String shape1, String shape2) { //승,패 모양 모두 출력
        Map<String,String> result = new HashMap<String,String>();
        int shapeNum1 = GawiBawiBO.valueOf(shape1).getValue();
        int shapeNum2 = GawiBawiBO.valueOf(shape2).getValue();

        switch (shapeNum1*shapeNum2)  {
            case 2 : case 6 : //가위(1)vs바위(2), 바위(2)vs보(3), 큰 숫자가 위너
                if(shapeNum1 > shapeNum2) {
                    result.put("win",GawiBawiBO.valueOf(shape1).getName());
                    result.put("lose",GawiBawiBO.valueOf(shape2).getName());
                } else {
                    result.put("win",GawiBawiBO.valueOf(shape2).getName());
                    result.put("lose",GawiBawiBO.valueOf(shape1).getName());
                }
                break;
            case 3 : //가위(1)vs보(3), 작은 숫자가 위너
                if(shapeNum1 < shapeNum2) {
                    result.put("win",GawiBawiBO.valueOf(shape1).getName());
                    result.put("lose",GawiBawiBO.valueOf(shape2).getName());
                } else {
                    result.put("win",GawiBawiBO.valueOf(shape2).getName());
                    result.put("lose",GawiBawiBO.valueOf(shape1).getName());
                }
                break;
        }
        return result;
    }

    //승리팀 점수 계산
    //승리팀의 팀원 인덱스를 찾아 스코어보드 점수를 더 한다
    //문자열에서 승자 인덱스 찾기
    public int[] scoring(int n, String hands, String shape, int roundScore ){
        int[] score = new int[n];

        for (int i = 0; i < hands.length(); i++) {
            if(shape.equals(String.valueOf(hands.charAt(i)))) { //승리팀원찾기
                score[i] = roundScore;
            } else {
                score[i] = 0;
            }
        }
        return score;
    }

    //팀원 수 세기
    public int countTeammate(String hand, String playerHands) {
        int count = 0;
        for (int i = 0; i < playerHands.length(); i++) {
            if(hand.equals(playerHands.charAt(i))) count++;
        }
        return count;
    }

}
