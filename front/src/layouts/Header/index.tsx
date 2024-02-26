import React from 'react';

const Header = () => {
  return (
    <div id='header-wrap'>
      <div className='header-top-box'>
        <div className='header-left-box'></div>
        <div className='header-middle-box'></div>
        <div className='header-right-box'></div>
      </div>

      <div className='header-bottom-box'>
        <div>식품</div>
        <div>가전제품</div>
      </div>
    </div>
  );
};

export default Header;