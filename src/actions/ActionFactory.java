package actions;

import fileio.ActionInput;

public final class ActionFactory {
    private ActionFactory() {
    }

    /**
     * Used for "on page" and "database" actions, which are differentiated by feature
     * @param action the action input
     * @return the action specified by the "feature" field
     */
    private static Action getActionByFeature(ActionInput action) {
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
            case "add" -> {
                return new AddMovieAction(action);
            }
            case "delete" -> {
                return new DeleteMovieAction(action);
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Turns input actions into Action objects
     *
     * @param action the ActionInput
     * @return the Action object
     */
    public static Action createAction(final ActionInput action) {
        switch (action.getType()) {
            case "change page" -> {
                return new ChangePageAction(action);
            }
            case "on page", "database" -> {
                return getActionByFeature(action);
            }
            case "subscribe" -> {
                return new SubscribeAction(action);
            }
            case "back" -> {
                return new GoBackAction();
            }
            default -> {
                return null;
            }
        }
    }
}
