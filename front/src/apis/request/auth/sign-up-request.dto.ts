export default interface SignUpRequestDto{
  email: string | null | undefined;
  profileImage: string,
  nickname: string;
  password: string;
  passwordCheck: string
}