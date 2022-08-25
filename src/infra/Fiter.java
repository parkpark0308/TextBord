package infra;

import config.ApplicationConfig;

public interface Fiter {

    public Request request;

    public Fiter(Request request){
        this.request = request;
    }

    private boolean isAnonymous(String url){
        String [] anonymousUrlList = ApplicationConfig.anonymousUriList;

        for(String regUrl : anonymousUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private boolean isNeedAuth(String url){

        String[] hasAuthUrlList = ApplicationConfig.hasAuthUriList;

        for(String regUrl : hasAuthUrlList){
            if (regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }
}
