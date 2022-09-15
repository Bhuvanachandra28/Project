import React from 'react';
import background from '../assets/images/plane3.jpg';


function ErrorWorld(props) {
    return (
        <div className="container-fluid" style={styling.bg}>
            <h1 style={styling.heading}>
                URL was Wrong Bro please check!!
            </h1>
        </div>
    );
}

let styling = {
    bg : {
        background : `url(${background}) no-repeat fixed center`,
        width : "100%",
        height : '100vh'
    },
    heading : {
        textAlign: "center",
        position: "relative",
        top: "15%",
        fontFamily: "fantasy",
        border: "2px #333333",
        color: "#333333"
    }
}

export default ErrorWorld;