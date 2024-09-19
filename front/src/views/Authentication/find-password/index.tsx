import React, { ChangeEvent, useRef, useState } from "react";
import "./style.css";
import InputBox from "../../../components/InputBox";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import { useLoginUserStore } from "../../../store";

const FindPassword = () => {
  const { loginUser, setLoginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();
  const navigate = useNavigate();

  const authNumberRef = useRef<HTMLInputElement | null>(null);
  const [authNumber, setAuthNumber] = useState<string>("");
  const [authNumberError, setAuthNumberError] = useState<boolean>(false);
  const [authNumberErrorMessage, setAuthNumberErrorMessage] =
    useState<string>("");

  const userEmailRef = useRef<HTMLInputElement | null>(null);
  const [userEmail, setUserEmail] = useState<string>("");
  const [userEmailError, setUserEmailError] = useState<boolean>(false);

  const [userEmailErrorMessage, setUserEmailErrorMessage] =
    useState<string>("");

  const onUserEmailHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setUserEmail(value);
    setUserEmailError(false);
    setUserEmailErrorMessage("");
  };

  const onAuthNumberChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setAuthNumber(value);
    setAuthNumberError(false);
    setAuthNumberErrorMessage("");
  };

  const back = () => {
    navigate(-1);
  };

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

  const modifyPasswordCheckRef = useRef<HTMLInputElement | null>(null);
  const [modifyPasswordCheck, setModifyPasswordCheck] = useState<string>("");
  const [modifyPasswordCheckError, setModifyPasswordCheckError] =
    useState<boolean>(false);

  const [modifyPasswordCheckErrorMessage, setModifyPasswordCheckErrorMessage] =
    useState<string>("");
  const onModifyPasswordCheckhangeHandler = (
    event: ChangeEvent<HTMLInputElement>
  ) => {
    const { value } = event.target;
    setModifyPasswordCheck(value);
    setModifyPasswordCheckError(false);
    setModifyPasswordCheckErrorMessage("");
  };

  const onFindPasswordBtnClickHandler = async () => {
    let error = false;
    // if (modifyNickname.length === 0) {
    //   setModifyNicknamedError(true);
    //   setModifyNicknameErrorMessage("변경하실 닉네임을 입력해주세요.");
    //   error = true;
    // }

    if (authNumber.length === 0) {
      setAuthNumberError(true);
      setAuthNumberErrorMessage("현재 사용중인 비밀번호를 입력해주세요.");
      error = true;
    }

    if (error) {
      return;
    }

    if (!error) {
      // 아이디 찾기 api
    }
  };

  return (
    <div className={"find-passwordModify-wrap"}>
      <div className={"find-passwordModify-container"}>
        <div className={"find-passwordModify-top"}>
          <div className={"find-passwordModify-title"}>비밀번호 찾기</div>
        </div>
        <div className={"find-passwordModify-mid"}>
          <InputBox
            ref={userEmailRef}
            label="이메일"
            type={"text"}
            placeholder="이메일을 입력해주세요."
            value={userEmail}
            onChange={onUserEmailHandler}
            error={userEmailError}
            message={userEmailErrorMessage}
          />

          <InputBox
            ref={authNumberRef}
            label="인증번호"
            type={"password"}
            placeholder="이메일로 발송해준 인증번호를 입력해주세요."
            value={authNumber}
            onChange={onAuthNumberChangeHandler}
            error={authNumberError}
            message={authNumberErrorMessage}
          />

          <InputBox
            ref={modafiyPasswordRef}
            label="새로운 비밀번호"
            type={"password"}
            placeholder="새로운 비밀번호를 입력해주세요."
            value={modifyPassword}
            onChange={onModifyPasswordChangeHandler}
            error={modifyPasswordError}
            message={modifyPasswordErrorMessage}
          />
          <InputBox
            ref={modifyPasswordCheckRef}
            label="변경할 비밀번호 확인"
            type={"password"}
            placeholder="확인을 위해 다시 입력해주세요."
            value={modifyPasswordCheck}
            onChange={onModifyPasswordCheckhangeHandler}
            error={modifyPasswordCheckError}
            message={modifyPasswordCheckErrorMessage}
          />
        </div>

        <div className={"find-passwordModify-bottom"}>
          <div
            className={"find-passwordModify-btn"}
            onClick={onFindPasswordBtnClickHandler}
          >
            변경
          </div>
          <div className={"find-passwordModify-cancel"} onClick={back}>
            이전
          </div>
        </div>
      </div>
    </div>
  );
};

export default FindPassword;
