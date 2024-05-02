import { useEffect, useState } from "react";

// countPerPage : 한페이지당 몇개 보여줄지
const usePagination = (countPerPage: number) => {
  //  state: 전체 item 수    //
  const [countPerItem,setCountPerItem] = useState<number>(10);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalCount, setTotalCount] = useState<number>(0);
  const [section, setSection] = useState<number>(0);
  const [totalSection, setTotalSection] = useState<number>(0);
  const [start, setStart] = useState<number>(1);
  const [end, setEnd] = useState<number>(1);
  const [pageList, setPageList] = useState<number[]>([]);
  const [totalPage, setTotalPage] = useState<number>(0);
  //  state: 현재 페이지 번호 상태   //
  // 지금 몇번째 페이지에 있는지에 대한걸 저장할 상태

  // 섹션 1부터,
  const set = () => {
    // setTotalPage
    setSection(Math.floor(currentPage / countPerPage) + 1); // 현재 섹션
    const endSection = Math.ceil( totalCount / countPerPage );
    setTotalSection(endSection); // 총 섹션
    const startNumber = (section - 1) * countPerPage + 1;
    setStart(startNumber);
    const endPage = totalPage > section * countPerPage ? section * countPerPage : totalPage;
      //                          20         1      *       10               10             :      20
    setEnd(endPage);
    console.log("end = " + end);
    const pages = Array.from({ length: end - start + 1}, (_, index) => start + index);
    setPageList(pages);
    console.log(`currentPage = ${currentPage} / totalpage = ${totalPage} / start = ${start} / end = ${end} /
    section = ${section} / totalCount = ${totalCount} / countPerPage = ${countPerItem}`);
  }

  useEffect(() => {
    const totalPageTemp = totalCount / countPerItem;
    setTotalPage(totalPageTemp);
    console.log(`totalPage = ${totalPageTemp}`)
    set();
  }, [totalCount]);

  useEffect(() => {
    set();
  }, [currentPage]);

  return {
    setCurrentPage, setTotalCount, setCountPerItem, countPerPage, countPerItem, start, end,totalSection, pageList, currentPage, section, setSection
  }

}


export default usePagination;
