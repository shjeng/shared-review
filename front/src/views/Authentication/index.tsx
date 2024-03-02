import React, { ChangeEvent, KeyboardEvent, useRef, useState } from "react";
import "./style.css";
import InputBox from "../../components/InputBox";
import SignInRequestDto from "../../apis/request/auth/sign-in-request.dto";

const Authentication = () => {
  //          state: 화면 상태        //
  const [view, setView] = useState<"sign-in" | "sign-up">("sign-in");

  const test = () => {};

  const onSignUpLinkClickHandler = () => {
    setView("sign-up");
  };

  const SignIn = () => {
    // state: 이메일 요소 참조 상태 //
    const emailRef = useRef<HTMLInputElement | null>(null);

    // state: 비밀번호 요소 참조 상태 //
    const passwordRef = useRef<HTMLInputElement | null>(null);

    //      state: 패스워드 타입 상태      //
    //      패스워드 가시성을 제어하기 위한(기본적으로 ***.. 아이콘을 누르면 입력한 비밀번호가 보임. 그렇기 때문에 하드코딩으로 password를 입력하지 않고 {passwordType}을 사용해 동적으로 처리)      //
    const [passwordType, setPasswordType] = useState<"text" | "password">(
      "password"
    );

    //      state: 에러 상태      //
    const [error, setError] = useState<boolean>(false);

    //      state: 이메일 상태        //
    const [email, setEmail] = useState<string>("");

    //      state: 패스워드 상태      //
    const [password, setPassword] = useState<string>("");

    //      event handler: 이메일 변경 이벤트 처리 함수      //
    // input에 value가 있을때랑 없을때랑 스타일 변화를 위한
    const [isEmailNotEmpty, setIsEmailNotEmpty] = useState<boolean>(false);
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const { value } = event.target;
      setIsEmailNotEmpty(!!value);
      setEmail(value);
    };

    //      event handler: 비밀번호 변경 이벤트 처리 함수      //
    // input에 value가 있을때랑 없을때랑 스타일 변화를 위한
    const [isPasswordNotEmpty, setIsPasswordNotEmpty] =
      useState<boolean>(false);

    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const { value } = event.target;
      setIsPasswordNotEmpty(!!value);
      setPassword(value);
    };

    //      event handler: 패스워드 버튼 클릭 이벤트 처리 함수      //
    const onPasswordButtonClickHandler = () => {
      if (passwordType === "text") {
        setPasswordType("password");
      } else {
        setPasswordType("text");
      }
    };

    return (
      <div id="sign-in-wrap">
        <div className="auth-sign-in-top">
          <div className="sign-in-title">로그인</div>
          <InputBox
            ref={emailRef}
            label="이메일 주소"
            type="text"
            placeholder="이메일 주소를 입력해주세요."
            error={error}
            value={email}
            onChange={onEmailChangeHandler}
          />

          <InputBox
            ref={passwordRef}
            label="패스워드"
            type={passwordType}
            placeholder="비밀번호를 입력해주세요."
            error={error}
            value={password}
            onChange={onPasswordChangeHandler}
            onButtonClick={onPasswordButtonClickHandler}
          />

          <div className="auth-card-bottom">
            {error && (
              <div className="auth-sign-in-error-box">
                <div className="auth-sign-in-error-message">
                  {
                    "이메일 주소 또는 비밀번호를 잘못 입력했습니다. \n입력하신 내용을 다시 확인해주세요."
                  }
                </div>
              </div>
            )}

            {/* 로그인 버튼 */}
            <div
              className={`login_button_off ${
                isEmailNotEmpty && isPasswordNotEmpty ? "login_button_on" : ""
              }`}
            >
              {"로그인"}
            </div>

            {/* <div
              className="black-large-full-button"
              onClick={onSignInButtonClickHandler}
            >
              {"로그인"}
            </div> */}
          </div>
        </div>

        <hr />

        <div className="auth-sign-in-bottom">
          <div className="sign-in-access-options">
            <ul>
              <li>
                <div className="auth-description-link" onClick={test}>
                  {"이메일 찾기"}
                </div>
              </li>
              <li>
                <div className="auth-description-link" onClick={test}>
                  {"비밀번호 찾기"}
                </div>
              </li>
              <li>
                <div
                  className="auth-description-link"
                  onClick={onSignUpLinkClickHandler}
                >
                  {"회원가입"}
                </div>
              </li>
            </ul>
          </div>

          <div className="social_login_box">
            <div className="social_login_image-box-g">
              <div className="social_login_image-g"></div>
              <div className="social_login_title">구글 로그인</div>
            </div>
            <div className="social_login_image-box-k">
              <div className="social_login_image-k"></div>
              <div className="social_login_title">카카오 로그인</div>
            </div>
            <div className="social_login_image-box-n">
              <div className="social_login_image-n"></div>
              <div className="social_login_title">네이버 로그인</div>
            </div>
          </div>
        </div>
      </div>
    );
  };

  const SignUp = () => {
    //        state: 이메일 요소 참조 상태      //
    const emailRef = useRef<HTMLInputElement | null>(null);

    //        state: 이메일 인증번호 요소 참조 상태      //
    const emailCertifiedRef = useRef<HTMLInputElement | null>(null);

    //        state: 이메일 상태            //
    const [email, setEmail] = useState<string>("");

    //        state: 이메일 에러 상태       //
    const [isEmailError, setEmailError] = useState<boolean>(false);

    //        state: 이메일 에러 메세지 상태       //
    const [emailErrorMessage, setEmailErrorMessage] = useState<string>("");

    //        state: 이메일 인증번호 상태            //
    const [emailCertified, setEmailCertified] = useState<string>("");

    //        state: 패스워드 요소 참조 상태     //
    const passwordRef = useRef<HTMLInputElement | null>(null);

    //        state: 패스워드 타입 상태       //
    const [passwordType, setPasswordType] = useState<"text" | "password">(
      "password"
    );

    //        state: 패스워드 상태          //
    const [password, setPassword] = useState<string>("");

    //        state: 패스워드 에러 상태       //
    const [isPasswordError, setPasswordError] = useState<boolean>(false);
    //        state: 패스워드 에러 메세지 상태       //
    const [passwordErrorMessage, setPasswordErrorMessage] =
      useState<string>("");

    //        state: 패스워드 확인 요소 참조 상태      //
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);

    //        state: 패스워드 확인 타입 상태       //
    const [passwordCheckType, setPasswordCheckType] = useState<
      "text" | "password"
    >("password");

    //        state: 패스워드 확인 상태          //
    const [passwordCheck, setPasswordCheck] = useState<string>("");

    //        state: 패스워드 확인 에러 상태       //
    const [isPasswordCheckError, setPasswordCheckError] =
      useState<boolean>(false);

    //        state: 패스워드 확인 에러 메세지 상태       //
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] =
      useState<string>("");

    //        state: 휴대폰 요소 참조 상태      //
    const telNumberRef = useRef<HTMLInputElement | null>(null);

    //        state: 핸드폰 번호 상태           //
    const [telNumber, setTelNumber] = useState<string>("");

    //        state: 핸드폰 번호 에러 상태       //
    const [isTelNumberError, setTelNumberError] = useState<boolean>(false);

    //        state: 핸드폰 번호 에러 메세지 상태       //
    const [telNumberErrorMessage, setTelNumberErrorMessage] =
      useState<string>("");

    //        state: 핸드폰 번호 상태           //
    const [telNumberCertified, setTelNumberCertified] = useState<string>("");

    // event handler: 이메일 변경 이벤트 처리     //
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setEmailError(false);
      setEmail(value);
      setEmailErrorMessage("");
    };

    // event handler: 패스워드 변경 이벤트 처리     //
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPasswordError(false);
      setPassword(value);
      setPasswordErrorMessage("");
    };

    // event handler: 패스워드 확인 변경 이벤트 처리     //
    const onPasswordCheckChangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const { value } = event.target;
      setPasswordCheck(value);
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage("");
    };

    // event handler: 핸드폰 번호 변경 이벤트 처리      //
    const onTelNumberChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setTelNumber(value);
      setTelNumberError(false);
      setTelNumberErrorMessage("");
    };
    return (
      <div id="sign-up-wrap">
        <div className="auth-sign-up-top">
          <div className="sign-up-title">회원가입</div>
          <div className="join_input_box">
            <div className="join-Certified-Input-Box">
              <InputBox
                ref={emailRef}
                label="이메일"
                type="text"
                placeholder="이메일을 입력해주세요."
                value={email}
                onChange={onEmailChangeHandler}
                error={isEmailError}
                message={emailErrorMessage}
              />
              <div className="email-certification-btn">{"인증번호 발송"}</div>
            </div>

            <div className="join-Certified-Input-Box">
              <InputBox
                ref={emailCertifiedRef}
                label="인증번호"
                type="text"
                placeholder="인증번호를 입력해주세요."
                value={emailCertified}
                onChange={onEmailChangeHandler}
                error={isEmailError}
                message={emailErrorMessage}
              />
              <div className="email-certification-btn">{"인증번호 확인"}</div>
            </div>
            <InputBox
              ref={passwordRef}
              label="비밀번호"
              type={passwordType}
              placeholder="비밀번호를 입력해주세요."
              value={password}
              onChange={onPasswordChangeHandler}
              error={isPasswordError}
              message={passwordErrorMessage}
            />
            <InputBox
              ref={passwordCheckRef}
              label="비밀번호 확인"
              type={passwordCheckType}
              placeholder="비밀번호를 다시 입력해주세요."
              value={passwordCheck}
              onChange={onPasswordCheckChangeHandler}
              error={isPasswordCheckError}
              message={passwordCheckErrorMessage}
            />

            <div className="join-Certified-Input-Box">
              <InputBox
                ref={telNumberRef}
                label="핸드폰 번호"
                type="text"
                placeholder="핸드폰 번호를 입력해주세요."
                value={telNumber}
                onChange={onTelNumberChangeHandler}
                error={isTelNumberError}
                message={telNumberErrorMessage}
              />
              <div className="email-certification-btn">{"인증번호 발송"}</div>
            </div>

            <div className="join-Certified-Input-Box">
              <InputBox
                ref={telNumberRef}
                label="인증번호"
                type="text"
                placeholder="인증번호를 입력해주세요."
                value={telNumberCertified}
                onChange={onTelNumberChangeHandler}
                error={isTelNumberError}
                message={telNumberErrorMessage}
              />
              <div className="email-certification-btn">{"인증번호 확인"}</div>
            </div>
          </div>
        </div>

        <div className="auth-join-bottom">
          <div className="black-large-full-button">{"다음"}</div>

          <div className="auth-cancel-button">{"취소"}</div>
        </div>
      </div>
    );
  };

  return (
    <div id="auth-wrapper">
      <div className="auth-body-box">
        {view === "sign-in" && <SignIn />}
        {view === "sign-up" && <SignUp />}
      </div>
    </div>
  );
};

export default Authentication;
