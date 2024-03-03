import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import ResponseDto from "./response/response.dto";
import { NicknameChkResponseDto, SignInResponseDto } from "./response/auth";

const DOMAIN = 'http://localhost:8080';
const API_DOMAIN = `${DOMAIN}/api/v1`;
const authorization = (accessToken: string) => {
  return {headers: {Authorization: `Bearer ${accessToken}`}}
}
// GET // 

const NICKNAME_CHK = (nickname:string) => `${API_DOMAIN}/nickname-chk?nickname=${nickname}`; // 닉네임 중복 확인
export const nicknameDuplChk = async(nickname: string) => {
  const result = await axios.get(NICKNAME_CHK(nickname))
    .then(response => {
      const responseBody:NicknameChkResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

// POST //
const SIGN_IN = () => `${API_DOMAIN}/login`;
export const signInRequest = async(requestBody: SignInRequestDto) => {
  const result = await axios.post(SIGN_IN(),requestBody)
    .then(response =>{
      const responseBody:SignInResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}

const SIGN_UP = `${API_DOMAIN}/sign-up`;
export const signUpRequest = async(requestBody: SignUpRequestDto) => {


}