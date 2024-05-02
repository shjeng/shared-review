import { useEffect, useState } from "react";

// countPerPage : 한페이지당 몇개 보여줄지
const usePagination = (countPerPage: number) => {
  //  state: 전체 item 수    //
  const [countPerItem,setCountPerItem] = useState<number>(10);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [totalCount, setTotalCount] = useState<number>(0);
  const [currentSection, setCurrentSection] = useState<number>(0);
  const [totalSection, setTotalSection] = useState<number>(0);
  const [startPage, setStartPage] = useState<number>(1);
  const [endPage, setEndPage] = useState<number>(1);
  const [pageList, setPageList] = useState<number[]>([]);
  const [totalPage, setTotalPage] = useState<number>(0);
  //  state: 현재 페이지 번호 상태   //
  // 지금 몇번째 페이지에 있는지에 대한걸 저장할 상태

  const setPageNumber = () => {
    const CURRENT_SECTION = Math.floor(currentPage / countPerPage);
    const FRIST_PAGE = countPerPage * currentSection + 1;
    const TOTAL_PAGE = totalCount / countPerItem;
    const END_PAGE = Math.min(countPerPage, TOTAL_PAGE);
    setTotalPage(TOTAL_PAGE);
    console.log(totalPage);
    console.log(countPerPage * currentSection > totalPage);
    setCurrentSection(CURRENT_SECTION);
    setStartPage(FRIST_PAGE);
    setEndPage(END_PAGE);

    console.log(`CURRENT_SECTION ${CURRENT_SECTION}/ FRIST_PAGE ${FRIST_PAGE} / END_PAGE ${END_PAGE} / TOTAL PAGE ${TOTAL_PAGE}`);
    console.log(`TOTAL COUNT ${totalCount} / countPerPage ${countPerPage}`);
  }

  useEffect(() => {
    console.log("useEffect currentPage " + currentPage);
    setPageNumber();
  }, [currentPage]);

  return {
    startPage,
    endPage,
    currentPage,
    setCurrentPage,
    setCurrentSection,
    setTotalCount,
    setCountPerItem

  }

}


export default usePagination;
