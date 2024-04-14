export const ResponseUtil = (responseBody: any) => {
    const { code } = responseBody;
    if (code === "VF") alert("유효성 검사 실패");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
        return false;
    }
    return responseBody;
}