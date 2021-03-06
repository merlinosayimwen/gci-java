package com.merlinweber.gci.concurrent.util;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

public final class InterruptableRunnables {

  // Prevent normal initialization of this util.
  private InterruptableRunnables() {}

  public static InterruptableRunnable nothrow(Runnable action) {
    return action::run;
  }

  public static InterruptableRunnable immediate() {
    return new ImmediateInterrupted();
  }

  private static final class ImmediateInterrupted implements InterruptableRunnable {
    @Nullable private String message;

    private ImmediateInterrupted() {
      this.message = null;
    }

    @Override
    public void run() throws InterruptedException{
      if (message == null) {
        throw new InterruptedException();
      }

      throw new InterruptedException(message);
    }
  }
}
