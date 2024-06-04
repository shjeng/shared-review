import React, {ChangeEvent, useEffect, useRef, useState} from "react";
import "./style.css";
import {useNavigate, useParams} from "react-router-dom";
import {editUser, getLoginUser, nicknameDuplChkRequest, saveImage} from "../../apis";
import {GetUserResponseDto} from "../../apis/response/user";
import ResponseDto from "../../apis/response/response.dto";
import {convertUrlToFile, ResponseUtil} from "../../utils";
import {User} from "../../types/interface";
import InputBox from "../../components/InputBox";
import {NicknameDupleChkResponseDto} from "../../apis/response/auth";
import {useCookies} from "react-cookie";
import {FileResponseDto} from "../../apis/response/file";
import {SignUpRequestDto} from "../../apis/request/auth";

const UserPage = () => {

  const [authSuccess, setAuthSuccess] = useState<boolean>(false);
  const EditPage = () => {
    const [cookies, setCookies] = useCookies();

    const navigate = useNavigate();
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);
    const nicknameRef = useRef<HTMLInputElement | null>(null);
    const profileImageRef = useRef<HTMLInputElement | null>(null);

    const {userEmail} = useParams();
    const [userInfo, setUserInfo] = useState<User>();

    const [profileImage, setProfileImage] = useState<string>('');
    const [file, setFile] = useState<File | null>();

    const [originNickname, setOriginNickname] = useState<string>('');
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
      (async () => {
        const file = await convertUrlToFile(profileImage);
        setFile(file);
      })();
    }, [userEmail]);
    const getMyInfoResponse = (response: GetUserResponseDto | ResponseDto | null) => {
      if (!ResponseUtil(response)) {
        return;
      }
      const result = response as GetUserResponseDto;
      setUserInfo(result.userDto);
      setNickname(result.userDto.nickname);
      setOriginNickname(result.userDto.nickname);
      setProfileImage(result.userDto.profileImage);

    }
    const editImageIconClick = () => {
      if (!profileImageRef.current) {
        return;
      }
      profileImageRef.current.click();
    }
    const profileImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const file = event.target.files?.[0];
      if (!file) {
        return;
      }
      const url = URL.createObjectURL(file);
      setProfileImage(url);
      setFile(file);
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
      if (!nickname) {
        setNicknameError(true);
        setNicknameErrorMessage('닉네임을 입력해주세요.');
        return;
      }
      if (password.length === 0) {
        setPasswordError(true);
        setPasswordErrorMessage("비밀번호를 입력해주세요!");
        error = true;
      }
      if (passwordCheck !== password || !passwordCheck) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage("비밀번호와 일치하지 않습니다.");
        error = true;
      }
      if (error) {
        return;
      }
      if (nickname !== originNickname) {
        nicknameDuplChkRequest(nickname).then(nicknameDuplChkResponse);
        if (!nicknameDupleCheck) {
          return;
        }
      }
      saveImage(cookies.accessToken,file).then(changeProfileImgResponse);
    }
    const nicknameDuplChkResponse = (responseBody: NicknameDupleChkResponseDto | ResponseDto | null) => {
      setVerifiedNickname('');
      ResponseUtil(responseBody);
      const { code } = responseBody as ResponseDto;
      if (code === "DN") {
        setNicknameError(true);
        setNicknameErrorMessage("이미 존재하는 닉네임입니다.");
      }
      const getResponse = responseBody as NicknameDupleChkResponseDto;
      setVerifiedNickname(getResponse.nickname);
      setNicknameDupleCheck(true);
    };

    const changeProfileImgResponse = (responseBody: FileResponseDto | ResponseDto | null) => {
      ResponseUtil(responseBody);
      const result = responseBody as FileResponseDto;
      const requestBody: SignUpRequestDto = {
        email: '',
        profileImage: result.savedName,
        nickname: verifiedNickname,
        password: password,
        passwordCheck: passwordCheck
      };
      editUser(cookies.accessToken, requestBody).then(editUserResponse);
    }
    const editUserResponse = () => {

    }
    const back = () => {
      navigate(-1);
    }
    return (
        <div id="user-page-content-wrap">
          <div id="top">
            <div className="top-top">
              <div className={"top-top-image box-img"} style={{backgroundImage: `url(${profileImage})`}}>
                <div className={"icon-box30 edit-btn"} onClick={editImageIconClick}></div>
                <input type={"file"} style={{display: "none"}} accept="image/*" ref={profileImageRef} onChange={profileImageChange}/>
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
              <InputBox ref={passwordCheckRef} label="비밀번호" type={"password"} placeholder="비밀번호 확인을 입력해주세요." value={passwordCheck} onChange={onPasswordCheckChangeHandler} error={passwordCheckError} message={passwordCheckErrorMessage}/>
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
