package com.jpm.application.service.impl;

import org.springframework.stereotype.Service;

import com.jpm.application.domain.ResultResponse;
import com.jpm.application.domain.UserInput;
import com.jpm.application.service.BaseService;
import com.jpm.application.system.exception.RequestException;

@Service("baseServiceImpl")
public class BaseServiceImpl implements BaseService {

	@Override
	public ResultResponse getMaxNumber(UserInput userInput) throws RequestException {
		
		int N = userInput.getNumber();
		int K = userInput.getDivisorCount();
		ResultResponse resultResponse = new ResultResponse();

				if(K==1){
					resultResponse.setResponseValue(1);
				}
				if(K%2==1){
					int len =(int)Math.sqrt(N);
					long w=0;
					while(len>0){
						w = (long)Math.pow(len,2);
						int root = (int)Math.sqrt(w);
						int factors=1;
						for(int k=1;k<root;k++){
							if(w%k==0)
								factors+=2;
							if(factors>K)
								break;
						}
						if(factors==K){
							resultResponse.setResponseValue(w);
						}
						len--;
					}
				}
				else{
					for(long j=N;j>=2;j--){
						int factors=0;
						int root = (int)Math.sqrt(j);
						for(int k=1;k<root;k++)
							if(j%k==0){
								factors+=2;
		                    if(factors>K)
		                        continue;
		                }
						if((root*root)==j)
							factors+=1;
		                else if(j%root==0)
		                    factors+=2;
						if(factors==K){
							resultResponse.setResponseValue(j);
						}
					}
				}
				resultResponse.setResponseValue(-1);
				return resultResponse;
			}
	}
