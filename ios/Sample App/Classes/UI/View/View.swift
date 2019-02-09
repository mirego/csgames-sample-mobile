import UIKit
import PinLayout

class View: UIView {
    fileprivate let containerView = UIView()

    fileprivate let weekDayLabel = UILabel()
    fileprivate let dayLabel = UILabel()
    fileprivate let monthLabel = UILabel()
    fileprivate let timeLabel = UILabel()
    
    init() {
        super.init(frame: CGRect.zero)

        backgroundColor = UIColor.haiti
        addSubview(containerView)

        weekDayLabel.font = UIFont.noeDisplayBold(ofSize: 36)
        weekDayLabel.textColor = UIColor.white
        containerView.addSubview(weekDayLabel)
        
        dayLabel.font = UIFont.noeDisplayBold(ofSize: 80)
        dayLabel.textColor = UIColor.orangeBlood
        containerView.addSubview(dayLabel)
        
        monthLabel.font = UIFont.noeDisplayBold(ofSize: 36)
        monthLabel.textColor = UIColor.white
        containerView.addSubview(monthLabel)
        
        timeLabel.font = UIFont.noeDisplayRegular(ofSize: 36)
        timeLabel.textColor = UIColor.white
        containerView.addSubview(timeLabel)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()

        weekDayLabel.pin.topCenter().sizeToFit()
        dayLabel.pin.below(of: weekDayLabel).hCenter().marginTop(20).sizeToFit()
        monthLabel.pin.below(of: dayLabel).hCenter().marginTop(10).sizeToFit()
        timeLabel.pin.below(of: monthLabel).hCenter().marginTop(30).sizeToFit()

        containerView.pin.wrapContent().center()
    }
}

extension View {
    func refreshDate(weekDay: String, day: String, month: String, time: String) {
        weekDayLabel.text = weekDay
        dayLabel.text = day
        monthLabel.text = month
        timeLabel.text = time

        setNeedsLayout()
    }
}
