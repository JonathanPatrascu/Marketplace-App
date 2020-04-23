import React, { Component } from "react";
import "./Footer.css";
import { Link } from "react-router-dom";
import facebookIcon from "../facebook_icon.png";
import instagramkIcon from "../Instagram_icon.png";
import youtubeIcon from "../youtube_icon.png";

class Footer extends Component {
  render() {
    return (
      <footer className="footer">
        <hr className="linie-orizontala" />
        <div className="subsol">
          <table>
            <tbody>
              <tr>
                <td className="col1Footer">
                  <Link to="/contulMeu" className="buton-login-footer">
                    <h2 className="footer-heading">Contul meu</h2>
                  </Link>
                </td>
                <td className="col1Footer">
                  <Link to="/informatii" className="buton-login-footer">
                    <h2 className="footer-heading">Intrebari frecvente</h2>
                  </Link>
                  <Link to="/termeniConditii" className="buton-login-footer">
                    <h2 className="footer-heading">Cum functioneaza?</h2>
                  </Link>
                  <Link to="/contact" className="buton-login-footer">
                    <h2 className="footer-heading">Contact</h2>
                  </Link>
                </td>
                <td className="col1Footer">
                  <h2 className="footer-heading">Social media</h2>
                  <a
                    href="https://www.facebook.com/jonathan.jp.73"
                    rel="noopener noreferrer"
                    target="_blank"
                  >
                    <img
                      className="facebook-icon"
                      src={facebookIcon}
                      alt="Facebook Icon"
                    />
                  </a>
                  <a
                    href="https://www.instagram.com/?hl=ro"
                    rel="noopener noreferrer"
                    target="_blank"
                  >
                    <img
                      className="instagram-icon"
                      src={instagramkIcon}
                      alt="Instagram Icon"
                    />
                  </a>
                  <a
                    href="https://www.youtube.com/channel/UCPM0dvo3b1y8B22m2HJZyOA?view_as=subscriber"
                    rel="noopener noreferrer"
                    target="_blank"
                  >
                    <img
                      className="youtube-icon"
                      src={youtubeIcon}
                      alt="Youtube Icon"
                    />
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </footer>
    );
  }
}

export default Footer;
