public class DivisorSum {

    public void solution(int n){
        int answer = 0;

        for(int i=1; i<=n; i++){
            if(n%i == 0) answer += i;
        }

        System.out.println("약수의 합: "+answer);
    }

}
