import UIKit

extension UIColor {
    class var orangeBlood: UIColor { return UIColor(red: 255.0 / 255.0, green: 56.0 / 255.0, blue: 41.0 / 255.0, alpha: 1.0) }

    class var haiti: UIColor { return UIColor(red: 33.0 / 255.0, green: 30.0 / 255.0, blue: 37.0 / 255.0, alpha: 1.0) }
}

extension UIFont {
    class func noeDisplayBold(ofSize size: CGFloat) -> UIFont? { return UIFont(name: "NoeDisplay-Bold", size: size) }

    class func noeDisplayRegular(ofSize size: CGFloat) -> UIFont? { return UIFont(name: "NoeDisplay", size: size) }
}
