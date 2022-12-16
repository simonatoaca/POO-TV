package actions;

import fileio.ActionInput;

import java.util.Objects;

public final class ActionFactory {
    private ActionFactory() { }

    /**
     * Turns input actions into Action objects
     * @param action the ActionInput
     * @return the Action object
     */
    public static Action createAction(final ActionInput action) {
        if (Objects.equals(action.getType(), "change page")) {
            return new ChangePageAction(action);
        } else {
            switch (action.getFeature()) {
                case "login" -> {
                    return new LoginAction(action);
                }
                case "register" -> {
                    return new RegisterAction(action);
                }
                case "search" -> {
                    return new SearchAction(action);
                }
                case "filter" -> {
                    return new FilterAction(action);
                }
                case "buy tokens" -> {
                    return new BuyTokensAction(action);
                }
                case "buy premium account" -> {
                    return new BuyPremiumAccAction();
                }
                case "purchase" -> {
                    return new PurchaseAction(action);
                }
                case "watch" -> {
                    return new WatchAction(action);
                }
                case "like" -> {
                    return new LikeAction(action);
                }
                case "rate" -> {
                    return new RateAction(action);
                }
                default -> {
                    return null;
                }
            }
        }
    }
}
