import React, { ChangeEvent, useEffect, useRef, useState } from "react";
import "./style.css";
import { useNavigate, useParams } from "react-router-dom";
import {
  editUser,
  getLoginUser,
  getMyInfo,
  nicknameDuplChkRequest,
  passwordCheckRequest,
  saveTempImage,
  updateNickname,
  updatePassword,
} from "../../apis";
import { GetUserResponseDto } from "../../apis/response/user";
import ResponseDto from "../../apis/response/response.dto";
import { convertUrlToFile, ResponseUtil } from "../../utils";
import { User } from "../../types/interface";
import InputBox from "../../components/InputBox";
import { NicknameDupleChkResponseDto } from "../../apis/response/auth";
import { useCookies } from "react-cookie";
import { FileResponseDto } from "../../apis/response/file";
import { SignUpRequestDto } from "../../apis/request/auth";
import { useLoginUserStore } from "../../store";

const UserPage = () => {
  const [authSuccess, setAuthSuccess] = useState<boolean>(false);
  type PageType =
    | "index"
    | "edit"
    | "passwordModify"
    | "nickNameModif"
    | "deleteUser";
  const [currentPage, setCurrentPage] = useState<PageType>("index");
  const { userEmail } = useParams();
  const [profileImage, setProfileImage] = useState<string>("");
  const [file, setFile] = useState<File | null>();
  const [userInfo, setUserInfo] = useState<User>();
  const [nickname, setNickname] = useState<string>("");
  const [originNickname, setOriginNickname] = useState<string>("");

  useEffect(() => {
    if (!userEmail) {
      return;
    }
    getLoginUser(userEmail).then(getMyInfoResponse);
    (async () => {
      const file = await convertUrlToFile(profileImage);
      setFile(file);
    })();
  }, [userEmail, currentPage]);
  const getMyInfoResponse = (
    response: GetUserResponseDto | ResponseDto | null
  ) => {
    if (!ResponseUtil(response)) {
      return;
    }
    const result = response as GetUserResponseDto;
    setUserInfo(result.userDto);
    setNickname(result.userDto.nickname);
    setOriginNickname(result.userDto.nickname);
    setProfileImage(result.userDto.profileImage);
  };
  const EditPage = () => {
    const [cookies, setCookies] = useCookies();
    const { setLoginUser } = useLoginUserStore();

    const navigate = useNavigate();
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);
    const nicknameRef = useRef<HTMLInputElement | null>(null);
    const profileImageRef = useRef<HTMLInputElement | null>(null);

    const [nicknameError, setNicknameError] = useState<boolean>(false);
    const [nicknameErrorMessage, setNicknameErrorMessage] =
      useState<string>("");
    const [nicknameDupleCheck, setNicknameDupleCheck] =
      useState<boolean>(false);
    const [verifiedNickname, setVerifiedNickname] = useState<string>("");

    const [password, setPassword] = useState<string>("");
    const [passwordError, setPasswordError] = useState<boolean>(false);
    const [passwordErrorMessage, setPasswordErrorMessage] =
      useState<string>("");

    const [passwordCheck, setPasswordCheck] = useState<string>("");
    const [passwordCheckError, setPasswordCheckError] =
      useState<boolean>(false);
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] =
      useState<string>("");

    const editImageIconClick = () => {
      if (!profileImageRef.current) {
        return;
      }
      profileImageRef.current.click();
    };
    const profileImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const file = event.target.files?.[0];
      if (!file) {
        return;
      }
      const url = URL.createObjectURL(file);
      setProfileImage(url);
      setFile(file);
    };
    const onNicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setNickname(value);
      setNicknameError(false);
      setNicknameErrorMessage("");
      setNicknameDupleCheck(false);
    };
    // const nicknameDuplChk = () => {
    //   if (nickname.length === 0) return;
    //   nicknameDuplChkRequest(nickname).then(nicknameDuplChkResponse);
    // }

    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPassword(value);
      setPasswordError(false);
      setPasswordErrorMessage("");
    };
    const onPasswordCheckChangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const { value } = event.target;
      setPasswordCheck(value);
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage("");
    };
    const editInfo = () => {
      let error = false;
      if (!nickname) {
        setNicknameError(true);
        setNicknameErrorMessage("닉네임을 입력해주세요.");
        return;
      }
      if (password.length === 0) {
        setPasswordError(true);
        setPasswordErrorMessage("비밀번호를 입력해주세요.");
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
      if (file) {
        const formData = new FormData();
        formData.append("file", file);
        saveTempImage(cookies.accessToken, formData).then(
          changeProfileImgResponse
        );
      } else {
      }
    };
    const nicknameDuplChkResponse = (
      responseBody: NicknameDupleChkResponseDto | ResponseDto | null
    ) => {
      setVerifiedNickname("");
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

    const changeProfileImgResponse = (
      responseBody: FileResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) {
        alert("서버의 응답이 없습니다.");
        return;
      }
      const { code } = responseBody as ResponseDto;
      if (code) {
        alert("사진이 업로드가 되지 않았습니다.");
        return;
      }
      const result = responseBody as FileResponseDto;
      const requestBody: SignUpRequestDto = {
        email: "",
        profileImage: result.savedName,
        nickname: verifiedNickname,
        password: password,
        passwordCheck: passwordCheck,
      };
      editUser(cookies.accessToken, requestBody).then(editUserResponse);
    };
    const editUserResponse = (response: ResponseDto | null) => {
      ResponseUtil(response);
      const result = response as ResponseDto;
      setAuthSuccess(false);
      getMyInfo(cookies.accessToken).then(getLoginUserResponse);
    };
    const getLoginUserResponse = (
      responseBody: GetUserResponseDto | ResponseDto | null
    ) => {
      ResponseUtil(responseBody);
      if (!responseBody) return;
      const { userDto } = responseBody as GetUserResponseDto;
      setLoginUser(userDto);
    };

    const passwordModifyPage = () => {
      setCurrentPage("passwordModify");
    };

    const nickNameModify = () => {
      setCurrentPage("nickNameModif");
    };

    const deleteUserPage = () => {
      setCurrentPage("deleteUser");
    };

    return (
      <div id="user-page-content-wrap">
        <div className="user-page-main">
          <div className="top-left">
            <div className="top-left-item1">내프로필</div>
            <div className="top-left-item2">프로필 수정</div>
            <div className="top-left-item3">비밀번호 변경</div>
          </div>

          <div id="top-right">
            <div className="top-top">
              <div
                className={"top-top-image box-img"}
                style={{ backgroundImage: `url(${profileImage})` }}
              >
                <div className={"edit-btn"} onClick={editImageIconClick}></div>
                <input
                  type={"file"}
                  style={{ display: "none" }}
                  accept="image/*"
                  ref={profileImageRef}
                  onChange={profileImageChange}
                />
              </div>
              <div className={"top-top-infobox"}>
                <div className={"top-top-infobox-name"}>
                  {userInfo?.nickname}
                </div>
                <div className={"top-top-infobox-email"}>{userInfo?.email}</div>
                <div className={"top-top-infobox-email"}>
                  {userInfo?.admin === "NORMAL" && "일반회원"}
                  {userInfo?.admin === "MANAGER" && "관리자"}
                </div>
              </div>
            </div>

            <div className={"top-middle"}>
              <div className="my-profile-item1">
                <div className="my-profile-nickname">닉네임</div>

                <div className="my-profile-nickname-box">
                  <span className="my-profile-nickname-text">{nickname}</span>
                  <div
                    className="my-profile-modify-btn1"
                    onClick={nickNameModify}
                  >
                    수정
                  </div>
                </div>
              </div>

              <div className="my-profile-item2">
                <div className="my-profile-password">비밀번호</div>

                <div className="my-profile-password-box">
                  <span className="my-profile-password-text">●●●●●●●●</span>
                  <div
                    className="my-profile-modify-btn2"
                    onClick={passwordModifyPage}
                  >
                    수정
                  </div>
                </div>
              </div>
            </div>

            <div className={"top-bottom"}>
              {/* user테이블에 active 컬럼 추가해서 탈퇴 표시하기? */}
              <div className={"user-modify"} onClick={deleteUserPage}>
                회원탈퇴
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  };

  const Index = () => {
    const [cookies, setCookies] = useCookies();

    const inputRef = useRef<HTMLInputElement | null>(null);
    const [password, setPassword] = useState<string>("");

    const passwordChange = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPassword(value);
    };
    const passwordKeydown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== "Enter") return;
      passwordCheck();
    };
    const passwordCheck = () => {
      passwordCheckRequest(cookies.accessToken, password).then(
        passwordCheckResponse
      );
    };
    const passwordCheckResponse = (response: ResponseDto | null) => {
      if (!response) {
        alert("서버 에러");
        return;
      }
      const { code } = response;
      if (code === "NU") {
        alert("비밀번호가 틀렸습니다.");
        return;
      }
      ResponseUtil(response);
      if (code === "SU") {
        // setAuthSuccess(true);
        setCurrentPage("edit");
      }
    };
    return (
      <div className={"user-info-box-wrap"}>
        <div className={"user-info-box"}>
          <div className={"user-info-title"}>본인확인</div>
          <div className={"user-info-input-box"}>
            <div className={"input-box"}>
              <input
                placeholder={"비밀번호를 입력해주세요."}
                onChange={passwordChange}
                onKeyDown={passwordKeydown}
                type={"password"}
                value={password}
                className={"password-input"}
                ref={inputRef}
              />
            </div>
            <div className={"button"} onClick={passwordCheck}>
              입력
            </div>
          </div>
        </div>
      </div>
    );
  };

  const PassWordModify = () => {
    const navigate = useNavigate();
    const [cookies, setCookies] = useCookies();

    const modafiyPasswordRef = useRef<HTMLInputElement | null>(null);
    const [modifyPassword, setModifyPassword] = useState<string>("");
    const [modifyPasswordError, setModifyPasswordError] =
      useState<boolean>(false);
    const [modifyPasswordErrorMessage, setModifyPasswordErrorMessage] =
      useState<string>("");

    const onModifyPasswordChangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const { value } = event.target;
      setModifyPassword(value);
      setModifyPasswordError(false);
      setModifyPasswordErrorMessage("");
    };

    const passwordRef = useRef<HTMLInputElement | null>(null);
    const [password, setPassword] = useState<string>("");
    const [passwordError, setPasswordError] = useState<boolean>(false);
    const [passwordErrorMessage, setPasswordErrorMessage] =
      useState<string>("");

    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPassword(value);
      setPasswordError(false);
      setPasswordErrorMessage("");
    };

    const modifyPasswordCheckRef = useRef<HTMLInputElement | null>(null);
    const [modifyPasswordCheck, setModifyPasswordCheck] = useState<string>("");
    const [modifyPasswordCheckError, setModifyPasswordCheckError] =
      useState<boolean>(false);

    const [
      modifyPasswordCheckErrorMessage,
      setModifyPasswordCheckErrorMessage,
    ] = useState<string>("");
    const onModifyPasswordCheckhangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const { value } = event.target;
      setModifyPasswordCheck(value);
      setModifyPasswordCheckError(false);
      setModifyPasswordCheckErrorMessage("");
    };

    const back = () => {
      setCurrentPage("edit");
    };

    const passwordModify = async () => {
      let error = false;
      if (password.length === 0) {
        setPasswordError(true);
        setPasswordErrorMessage("사용중인 비밀번호를 입력해주세요.");
        error = true;
      }

      // 비밀번호가 입력된 경우 비밀번호 확인
      if (password.length > 0) {
        const response = await passwordCheckRequest(
          cookies.accessToken,
          password
        );

        if (response?.code === "NU") {
          setPasswordError(true);
          setPasswordErrorMessage("현재 비밀번호가 일치하지 않습니다.");
          error = true;
        }
      }

      if (modifyPassword === password) {
        setModifyPasswordError(true);
        setModifyPasswordErrorMessage(
          "현재 비밀번호와 변경하실 비밀번호가 같습니다."
        );
        error = true;
      }

      if (modifyPassword.length === 0) {
        setModifyPasswordError(true);
        setModifyPasswordErrorMessage("변경할 비밀번호를 입력해주세요.");
        error = true;
      }

      if (modifyPasswordCheck !== modifyPassword || !modifyPasswordCheck) {
        setModifyPasswordCheckError(true);
        setModifyPasswordCheckErrorMessage(
          "변경할 비밀번호와 일치하지 않습니다."
        );
        error = true;
      }

      if (error) {
        return;
      }

      if (!error) {
        updatePassword(cookies.accessToken, password, modifyPassword).then(
          updatePasswordResponse
        );
      }
    };

    const updatePasswordResponse = (response: ResponseDto | null) => {
      if (response?.code === "SU") {
        alert(response?.message);
        setCurrentPage("edit");
      } else {
        alert("오류");
        return;
      }
    };

    return (
      <div className={"passwordModify-wrap"}>
        <div className={"passwordModify-container"}>
          <div className={"passwordModify-top"}>
            <div className={"passwordModify-title"}>비밀번호 변경</div>
          </div>
          <div className={"passwordModify-mid"}>
            <InputBox
              ref={passwordRef}
              label="현재 비밀번호"
              type={"password"}
              placeholder="현재 사용중인 비밀번호를 입력해주세요."
              value={password}
              onChange={onPasswordChangeHandler}
              error={passwordError}
              message={passwordErrorMessage}
            />

            <InputBox
              ref={modafiyPasswordRef}
              label="새 비밀번호"
              type={"password"}
              placeholder="변경할 비밀번호를 입력해주세요."
              value={modifyPassword}
              onChange={onModifyPasswordChangeHandler}
              error={modifyPasswordError}
              message={modifyPasswordErrorMessage}
            />
            <InputBox
              ref={modifyPasswordCheckRef}
              label="새 비밀번호 확인"
              type={"password"}
              placeholder="변경할 비밀번호 확인을 위해 다시 입력해주세요."
              value={modifyPasswordCheck}
              onChange={onModifyPasswordCheckhangeHandler}
              error={modifyPasswordCheckError}
              message={modifyPasswordCheckErrorMessage}
            />
          </div>

          <div className={"passwordModify-bottom"}>
            <div className={"passwordModify-btn"} onClick={passwordModify}>
              수정
            </div>
            <div className={"passwordModify-cancel"} onClick={back}>
              이전
            </div>
          </div>
        </div>
      </div>
    );
  };

  interface NickNameModifyProps {
    userInfo?: User;
  }
  const NickNameModify: React.FC<NickNameModifyProps> = ({ userInfo }) => {
    const { loginUser, setLoginUser } = useLoginUserStore();
    const [newNickname, setNewNickname] = useState("");
    const navigate = useNavigate();
    const [cookies, setCookies] = useCookies();

    const modafiyNicknameRef = useRef<HTMLInputElement | null>(null);
    const [modifyNickname, setModifyNickname] = useState<string>("");
    const [modifyNicknamedError, setModifyNicknamedError] =
      useState<boolean>(false);
    const [modifyNicknameErrorMessage, setModifyNicknameErrorMessage] =
      useState<string>("");

    const onModifyNicknameChangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const { value } = event.target;
      setModifyNickname(value);
      setModifyNicknamedError(false);
      setModifyNicknameErrorMessage("");
    };

    const passwordRef = useRef<HTMLInputElement | null>(null);
    const [password, setPassword] = useState<string>("");
    const [passwordError, setPasswordError] = useState<boolean>(false);
    const [passwordErrorMessage, setPasswordErrorMessage] =
      useState<string>("");

    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPassword(value);
      setPasswordError(false);
      setPasswordErrorMessage("");
    };

    const back = () => {
      setCurrentPage("edit");
    };

    const nicknameModify = async () => {
      let error = false;
      if (modifyNickname.length === 0) {
        setModifyNicknamedError(true);
        setModifyNicknameErrorMessage("변경하실 닉네임을 입력해주세요.");
        error = true;
      }

      if (modifyNickname === originNickname) {
        setModifyNicknamedError(true);
        setModifyNicknameErrorMessage(
          "현재 닉네임과 변경하실 닉네임이 같습니다."
        );
        error = true;
      }

      if (password.length === 0) {
        setPasswordError(true);
        setPasswordErrorMessage("현재 사용중인 비밀번호를 입력해주세요.");
        error = true;
      }

      if (error) {
        return;
      }

      if (!error) {
        updateNickname(cookies.accessToken, password, modifyNickname).then(
          updateNicknameResponse
        );
      }
    };

    const updateNicknameResponse = (response: ResponseDto | null) => {
      if (response?.code === "SU") {
        if (loginUser) {
          setLoginUser({
            ...loginUser,
            nickname: modifyNickname,
          });
        }
        alert(response?.message);
        setCurrentPage("edit");
      }

      if (response?.code === "NU") {
        setPasswordError(true);
        setPasswordErrorMessage(response?.message);
      }
    };

    return (
      <div className={"nicknameModify-wrap"}>
        <div className={"nicknameModify-container"}>
          <div className={"nicknameModify-top"}>
            <div className={"nicknameModify-title"}>닉네임 변경</div>
          </div>
          <div className={"nicknameModify-mid"}>
            <InputBox
              ref={modafiyNicknameRef}
              label="닉네임"
              type={"text"}
              placeholder="변경할 닉네임을 입력해주세요."
              value={modifyNickname}
              onChange={onModifyNicknameChangeHandler}
              error={modifyNicknamedError}
              message={modifyNicknameErrorMessage}
            />

            <InputBox
              ref={passwordRef}
              label="현재 비밀번호"
              type={"password"}
              placeholder="현재 사용중인 비밀번호를 입력해주세요."
              value={password}
              onChange={onPasswordChangeHandler}
              error={passwordError}
              message={passwordErrorMessage}
            />
          </div>

          <div className={"nicknameModify-bottom"}>
            <div className={"nicknameModify-btn"} onClick={nicknameModify}>
              수정
            </div>
            <div className={"nicknameModify-cancel"} onClick={back}>
              이전
            </div>
          </div>
        </div>
      </div>
    );
  };

  const DeleteUser = () => {
    return <div>회원탈퇴 페이지</div>;
  };

  // return <>{!authSuccess ? <Index /> : <EditPage />}</>;
  // return <>{<PassWordModify />}</>;
  return (
    <>
      {currentPage === "index" && <Index />}
      {currentPage === "edit" && <EditPage />}
      {currentPage === "passwordModify" && <PassWordModify />}
      {currentPage === "nickNameModif" && (
        <NickNameModify userInfo={userInfo} />
      )}
      {currentPage === "deleteUser" && <DeleteUser />}
    </>
  );
};

export default UserPage;
