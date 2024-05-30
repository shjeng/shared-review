import React, {ChangeEvent, useEffect, useRef, useState} from "react";
import "./style.css";
import {useNavigate, useParams} from "react-router-dom";
import {getLoginUser, nicknameDuplChkRequest} from "../../apis";
import {GetUserResponseDto} from "../../apis/response/user";
import ResponseDto from "../../apis/response/response.dto";
import {ResponseUtil} from "../../utils";
import {User} from "../../types/interface";
import InputBox from "../../components/InputBox";
import {NicknameDupleChkResponseDto} from "../../apis/response/auth";

const UserPage = () => {

  const [authSuccess, setAuthSuccess] = useState<boolean>(false);
  const EditPage = () => {
    const navigate = useNavigate();
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);
    const nicknameRef = useRef<HTMLInputElement | null>(null);

    const {userEmail} = useParams();
    const [userInfo, setUserInfo] = useState<User>();

    const [nickname, setNickname] = useState<string>('');
    const [nicknameError, setNicknameError] = useState<boolean>(false);
    const [nicknameErrorMessage, setNicknameErrorMessage] = useState<string>('');
    const [nicknameDupleCheck, setNicknameDupleCheck] = useState<boolean>(false);
    const [verifiedNickname, setVerifiedNickname] = useState<string>('');

    const [password, setPassword] = useState<string>('');
    const [passwordError, setPasswordError] = useState<boolean>(false);
    const [passwordErrorMessage, setPasswordErrorMessage] = useState<string>('');

    const [passwordCheck, setPasswordCheck] = useState<string>('');
    const [passwordCheckError, setPasswordCheckError] = useState<boolean>(false);
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] = useState<string>('');


    useEffect(() => {
      if (!userEmail) {
        return;
      }
      getLoginUser(userEmail).then(getMyInfoResponse);
    }, [userEmail]);
    const getMyInfoResponse = (response: GetUserResponseDto | ResponseDto | null) => {
      if (!ResponseUtil(response)) {
        return;
      }
      const result = response as GetUserResponseDto;
      setUserInfo(result.userDto);
      setNickname(result.userDto.nickname);
    }

    const onNicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const {value} = event.target;
      setNickname(value);
      setNicknameError(false);
      setNicknameErrorMessage("");
      setNicknameDupleCheck(false);
    }
    // const nicknameDuplChk = () => {
    //   if (nickname.length === 0) return;
    //   nicknameDuplChkRequest(nickname).then(nicknameDuplChkResponse);
    // }
    const nicknameDuplChkResponse = (
        responseBody: NicknameDupleChkResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) {
        alert("네트워크 이상입니다.");
        return;
      }
      const { code } = responseBody;
      if (code === "DN") {
        setNicknameError(true);
        setNicknameErrorMessage("이미 존재하는 닉네임입니다.");
      }
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;
      alert("중복 확인!");
      const getResponse = responseBody as NicknameDupleChkResponseDto;
      setVerifiedNickname(getResponse.nickname); // 검증 받은 이메일 저장.
    };
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const {value} = event.target;
      setPassword(value);
      setPasswordError(false);
      setPasswordErrorMessage("");
    }
    const onPasswordCheckChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const {value} = event.target;
      setPasswordCheck(value);
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage("");
    }
    const editInfo = () => {
      let error = false;
      if (password.length === 0) {
        setPasswordError(true);
        setPasswordErrorMessage("비밀번호를 입력해주세요!");
        error = true;
      }
      if (passwordCheck !== password) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage("비밀번호와 일치하지 않습니다.");
        error = true;
      }

      if (error) {
        return;
      }
      nicknameDuplChkRequest(nickname).then(nicknameDuplChkResponse);
    }
    const back = () => {
      navigate(-1);
    }
    return (
        <div id="user-page-content-wrap">
          <div id="top">
            <div className="top-top">
              <div className={"top-top-image box-img"} style={{backgroundImage: `url(${userInfo?.profileImage})`}}>
                <div className={"icon"}>아이콘</div>
              </div>
              <div className={"top-top-infobox"}>
                <div className={"top-top-infobox-name"}>{userInfo?.name} 아지르님</div>
                <div className={"top-top-infobox-email"}>{userInfo?.email}</div>
              </div>
            </div>

            <div className={"top-middle"}>
              <InputBox ref={nicknameRef} label="닉네임" type={"text"} placeholder="닉네임을 입력해주세요." value={nickname} onChange={onNicknameChangeHandler} error={nicknameError} message={nicknameErrorMessage}/>
              <InputBox ref={passwordRef} label="비밀번호" type={"password"} placeholder="비밀번호를 입력해주세요." value={password}
                        onChange={onPasswordChangeHandler} error={passwordError} message={passwordErrorMessage}/>
              <InputBox ref={passwordCheckRef} label="비밀번호" type={"password"} placeholder="비밀번호 확인을 입력해주세요." value={passwordCheck} onChange={onPasswordChangeHandler} error={passwordCheckError} message={passwordCheckErrorMessage}/>
            </div>

            <div className={"top-bottom"}>
              <div onClick={editInfo}>수정</div>
              <div onClick={back}>이전</div>
            </div>
          </div>
          <div id="middle"></div>
          <div id="bottom"></div>
        </div>
    );
  }
  return (
      <>
        {!authSuccess ?
            <EditPage />    
            :
        <div>테스트</div>
        }
        
      </>
  );

};

export default UserPage;
