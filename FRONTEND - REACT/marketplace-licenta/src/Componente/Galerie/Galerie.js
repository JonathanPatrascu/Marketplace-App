import React, { Component } from "react";
import "./Galerie.css";

export default class Galerie extends Component {
  state = { currImg: 0 };
  prevImg = () => {
    const { currImg } = this.state;
    const { images } = this.props;
    if (currImg > 0) return this.setState({ currImg: currImg - 1 });
    return this.setState({ currImg: images.length - 1 });
  };
  nextImg = () => {
    const { currImg } = this.state;
    const { images } = this.props;
    if (currImg < images.length - 1)
      return this.setState({ currImg: currImg + 1 });
    return this.setState({ currImg: 0 });
  };
  render() {
    const { currImg } = this.state;
    const { images } = this.props;
    return (
      <div className="galerie">
        <div
          className="image"
          style={{ backgroundImage: `url(${images[currImg]})` }}
        />
        <div className="left click-btn" onClick={this.prevImg}>
          <div class="arrow left" />
        </div>
        <div className="right click-btn" onClick={this.nextImg}>
          <div className="arrow right" />
        </div>
      </div>
    );
  }
}
